package blockchain;

import blockchain.blocks.CarrierBlock;
import blockchain.blocks.HardToMineBlock;
import blockchain.blocks.PrintableBlock;
import blockchain.blocks.SimpleBlock;
import blockchain.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class BlockFactory {
    private final Supplier<Long> magicNumberSupplier = (new Random())::nextLong;
    private final BlockChain blockChain;
    List<String> messages = new ArrayList<>();

    public BlockFactory(BlockChain blockChain) {
        this.blockChain = blockChain;
    }

    public Optional<Pair<PrintableBlock, String>> createBlock() {
        long id = blockChain.blocksCount() + 1;
        int security = blockChain.security();
        String previousBlockHash = blockChain.lastBlockHash();

        long startTime = now();
        PrintableBlock block = new CarrierBlock(
                new SimpleBlock(id, previousBlockHash, now()), getPayload()
        );
        PrintableBlock candidate = HardToMineBlock.mine(block, security, magicNumberSupplier);
        long endTime = now();

        synchronized (this) {
            Optional<Pair<PrintableBlock, String>> result = Optional.of(candidate)
                    .filter(blockChain::add)
                    .map(b -> new Pair<>(b, extraMessage(endTime - startTime, security)));
            if (result.isPresent()) {
                removeMessages();
            }
            return result;
        }
    }

    synchronized
    public void sendMessage(String message) {
        messages.add(message);
    }

    private void removeMessages() {
        messages = new ArrayList<>();
    }

    private String getPayload() {
        if (messages.isEmpty()) {
            return "no messages";
        }
        return "\n" + String.join("\n", messages);
    }

    public int blocksCount() {
        return blockChain.blocksCount();
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
