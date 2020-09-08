package blockchain;

import blockchain.blocks.Block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockChain {
    private final ArrayList<Block> blocks = new ArrayList<>();
    private final Object lock = new Object();
    private final AtomicInteger security = new AtomicInteger(0);
    private long lastBlockAddedAt = 0L;

    public boolean add(Block block) {
        synchronized (lock) {
            if (isValid(block)) {
                adjustSecurity();
                lastBlockAddedAt = System.currentTimeMillis();
                return blocks.add(block);
            } else {
                return false;
            }
        }
    }

    private void adjustSecurity() {
        if (blocks.isEmpty()) {
            security.incrementAndGet();
            return;
        }
        long lastBlockCreationTime = System.currentTimeMillis() - lastBlockAddedAt;
        if (lastBlockCreationTime > 60_000) {
            security.decrementAndGet();
        } else if (lastBlockCreationTime < 10_000) {
            security.incrementAndGet();
        }
    }

    private boolean isValid(Block block) {
        Iterator<Block> iterator = blocks.iterator();
        if (!iterator.hasNext()) {
            return true;
        }
        Block previousBlock = iterator.next();
        while (iterator.hasNext()) {
            Block currentBlock = iterator.next();
            if (!currentBlock.isNextFor(previousBlock)) {
                return false;
            }
            previousBlock = currentBlock;
        }
        return block.isNextFor(previousBlock);
    }

    public String lastBlockHash() {
        synchronized (lock) {
            if (blocks.size() > 0) {
                return blocks.get(blocks.size() - 1).hash();
            } else {
                return "0";
            }
        }
    }

    public int blocksCount() {
        return this.blocks.size();
    }

    public int security() {
        return security.get();
    }
}
