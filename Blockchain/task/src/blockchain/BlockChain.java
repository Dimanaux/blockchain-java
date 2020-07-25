package blockchain;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BlockChain {
    private final List<Block> blocks = new ArrayList<>();
    private final int security;
    private final Supplier<Long> magicNumberSupplier;
    private final Consumer<String> logger;

    /**
     * @param security            the number of zeroes in the beginning of hash of
     *                            each block required to create a block.
     * @param magicNumberSupplier generator of magic number
     */
    public BlockChain(int security, Supplier<Long> magicNumberSupplier, Consumer<String> logger) {
        this.security = security;
        this.magicNumberSupplier = magicNumberSupplier;
        this.logger = logger;
    }

    public BlockChain(int security) {
        this(security, (new Random())::nextLong, System.out::println);
    }

    public void createBlock() {
        long id = id();
        long startTime = System.currentTimeMillis();
        Block block = insecureBlock(id, magicNumberSupplier.get());
        while (!block.hash().startsWith(secureHashBeginning())) {
            block = insecureBlock(id, magicNumberSupplier.get());
        }
        long endTime = System.currentTimeMillis();
        logBlock(block, startTime, endTime);
        blocks.add(block);
    }

    public boolean isValid() {
        Iterator<Block> iterator = blocks.iterator();
        Block previousBlock = iterator.next();
        while (iterator.hasNext()) {
            Block currentBlock = iterator.next();
            if (!previousBlock.isPreviousOf(currentBlock)) {
                return false;
            }
            previousBlock = currentBlock;
        }
        return true;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        blocks.forEach(block -> joiner.add(block.toString()));
        return joiner.toString();
    }

    private Block insecureBlock(long id, long magicNumber) {
        return new Block(id, now(), lastBlockHash(), magicNumber);
    }

    private void logBlock(Block block, long startTime, long endTime) {
        String message = String.format(
                "%s\nBlock was generating for %s seconds\n",
                block, (endTime - startTime) / 1000
        );
        logger.accept(message);
    }

    private String secureHashBeginning() {
        return "0".repeat(security);
    }

    private String lastBlockHash() {
        if (blocks.size() > 0) {
            return blocks.get(blocks.size() - 1).hash();
        } else {
            return secureHashBeginning();
        }
    }

    private static long now() {
        return new Date().getTime();
    }

    private static long id() {
        return BlockChain.id++;
    }

    private static long id = 1;
}
