package blockchain;

import blockchain.blocks.Block;
import blockchain.blocks.ZeroBlock;
import blockchain.util.ZipWithNext;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockChain {
    private final ArrayList<Block> blocks = new ArrayList<>();
    private final Object lock = new Object();
    private final AtomicInteger security = new AtomicInteger(0);
    private long lastBlockAddedAt = 0L;
    private final Block zeroBlock = new ZeroBlock();

    public boolean add(Block block) {
        synchronized (lock) {
            if (isValid() && isValid(block)) {
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

    private boolean isValid() {
        for (var blockPair : new ZipWithNext<>(blocks)) {
            Block previousBlock = blockPair.first;
            Block currentBlock = blockPair.second;
            if (!currentBlock.isNextFor(previousBlock)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValid(Block block) {
        return block.isNextFor(lastBlock());
    }

    private Block lastBlock() {
        synchronized (lock) {
            if (blocks.size() > 0) {
                return blocks.get(blocks.size() - 1);
            } else {
                return zeroBlock;
            }
        }
    }

    public String lastBlockHash() {
        return lastBlock().hash();
    }

    public int blocksCount() {
        return this.blocks.size();
    }

    public int security() {
        return security.get();
    }
}
