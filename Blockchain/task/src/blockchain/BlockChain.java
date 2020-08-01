package blockchain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockChain {
    private final ArrayList<Block> blocks = new ArrayList<>();
    private final Object lock = new Object();
    private final AtomicInteger security = new AtomicInteger(0);

    public boolean add(Block block) {
        synchronized (lock) {
            if (isValid(block)) {
                adjustSecurity(block.getTimestamp());
                return blocks.add(block);
            } else {
                return false;
            }
        }
    }

    public int blocksCount() {
        return blocks.size();
    }

    public int security() {
        return security.get();
    }

    private void adjustSecurity(long timestamp) {
        if (blocks.isEmpty()) {
            security.incrementAndGet();
            return;
        }
        long lastBlockCreationTime = timestamp - blocks.get(blocks.size() - 1).getTimestamp();
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
            if (!previousBlock.isPreviousOf(currentBlock)) {
                return false;
            }
            previousBlock = currentBlock;
        }
        return block.hash().startsWith("0".repeat(security.get()))
                && previousBlock.isPreviousOf(block);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        synchronized (lock) {
            blocks.forEach(block -> joiner.add(block.toString()));
        }
        return joiner.toString();
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
}
