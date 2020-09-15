package blockchain;

import blockchain.printers.MapPrinter;

import java.util.Optional;

public class Miner {
    private final BlockFactory blockFactory;
    private final int id;

    public Miner(int id, BlockFactory blockFactory) {
        this.blockFactory = blockFactory;
        this.id = id;
    }

    public void mine() {
        while (blockFactory.acceptsBlocks()) {
            Optional<MapPrinter<String>> printer = blockFactory.createBlock(blockTransaction());
            synchronized (this.getClass()) {
                printer.ifPresent(p -> {
                    System.out.println(p.flush());
                    System.out.println();
                });
            }
        }
    }

    public void sendMoney() {
        while (blockFactory.acceptsBlocks()) {
            blockFactory.sendMessage("miner" + id + " sent 10 VC to miner 2");
            sleep();
        }
    }

    private static void sleep() {
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException ignored) {
        }
    }

    private String blockTransaction() {
        return String.format("miner%d\nminer%d gets 100 VC", id, id);
    }
}
