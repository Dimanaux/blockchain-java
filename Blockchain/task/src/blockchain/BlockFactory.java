package blockchain;

import blockchain.blocks.CarrierBlock;
import blockchain.blocks.HardToMineBlock;
import blockchain.blocks.PrintableBlock;
import blockchain.blocks.SimpleBlock;
import blockchain.printers.ChosenKeysNewLinePrinter;
import blockchain.printers.MapPrinter;
import blockchain.printers.OrderedMapPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class BlockFactory {
    private final Supplier<Long> magicNumberSupplier = (new Random())::nextLong;
    private final BlockChain blockChain;
    private final List<String> messages = new ArrayList<>();

    public BlockFactory(BlockChain blockChain) {
        this.blockChain = blockChain;
    }

    public Optional<MapPrinter<String>> createBlock(String blockTransaction) {
        long id = blockChain.blocksCount() + 1;
        int security = blockChain.security();
        String previousBlockHash = blockChain.lastBlockHash();

        long startTime = now();
        PrintableBlock block = new CarrierBlock(
                new SimpleBlock(id, previousBlockHash, now()), blockTransaction, getPayload()
        );
        PrintableBlock candidate = HardToMineBlock.mine(block, security, magicNumberSupplier);
        long endTime = now();

        Optional<PrintableBlock> result = Optional.of(candidate).filter(blockChain::add);
        result.ifPresent(_p -> removeMessages());
        return result.map(b -> {
            ChosenKeysNewLinePrinter<String> printer = new ChosenKeysNewLinePrinter<>(
                    new OrderedMapPrinter(
                            "Block:", extraMessage(endTime - startTime, security)
                    )
            );
            b.printTo(printer);
            return printer;
        });
    }

    public void sendMessage(String message) {
        synchronized (messages) {
            messages.add(message);
        }
    }

    private void removeMessages() {
        synchronized (messages) {
            messages.clear();
        }
    }

    private String getPayload() {
        synchronized (messages) {
            if (messages.isEmpty()) {
                return "No transactions";
            }
            return String.join("\n", messages);
        }
    }

    public boolean acceptsBlocks() {
        return blockChain.blocksCount() < 15;
    }

    private String extraMessage(long creationTime, int securityBefore) {
        StringBuilder message = new StringBuilder("Block was generating for ");
        message.append(creationTime / 1000).append(" seconds\n");
        if (securityBefore < blockChain.security()) {
            message.append("N was increased to ").append(blockChain.security());
        } else if (securityBefore == blockChain.security()) {
            message.append("N stays the same");
        } else {
            message.append("N was decreased by ").append(blockChain.security() - securityBefore);
        }
        return message.append("\n").toString();
    }

    private static long now() {
        return System.currentTimeMillis();
    }
}
