package blockchain;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BlockFactory {
    private final Supplier<Long> magicNumberSupplier;
    private final Consumer<String> logger;
    private final BlockChain blockChain;

    public BlockFactory(Supplier<Long> magicNumberSupplier, Consumer<String> logger, BlockChain blockChain) {
        this.magicNumberSupplier = magicNumberSupplier;
        this.logger = logger;
        this.blockChain = blockChain;
    }

    public boolean createBlock(String payload) {
        long id = blockChain.blocksCount() + 1;
        long startTime = System.currentTimeMillis();
        int security = blockChain.security();
        String previousBlockHash = blockChain.lastBlockHash();

        Block block;
        do {
            if (!blockChain.lastBlockHash().equals(previousBlockHash)) {
                return false;
            }
            block = insecureBlock(id, magicNumberSupplier.get(), previousBlockHash, payload);
        } while (!block.hash().startsWith(secureHashBeginning(security)));

        long endTime = System.currentTimeMillis();
        synchronized (this) {
            boolean added = blockChain.add(block);
            if (added) {
                logBlock(block, startTime, endTime, blockChain.security() - security);
                return true;
            }
            return false;
        }
    }

    public int blocksCount() {
        return blockChain.blocksCount();
    }

    private void logBlock(Block block, long startTime, long endTime, int secDiff) {
        String message = String.format(
                "%s\nBlock was generating for %s seconds\n",
                block, (endTime - startTime) / 1000
        );
        if (secDiff > 0) {
            message += "N was increased to " + blockChain.security() + "\n";
        } else if (secDiff == 0) {
            message += "N stays the same\n";
        } else {
            message += "N was decreased by " + secDiff + "\n";
        }
        logger.accept(message);
    }

    private Block insecureBlock(long id, long magicNumber, String previousBlockHash, String payload) {
        return new Block(id, now(), previousBlockHash, magicNumber, payload);
    }

    private String secureHashBeginning(int security) {
        return "0".repeat(security);
    }

    private static long now() {
        return System.currentTimeMillis();
    }
}
