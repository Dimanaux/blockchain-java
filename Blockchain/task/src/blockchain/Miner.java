package blockchain;

import blockchain.blocks.PrintableBlock;
import blockchain.printers.ChosenKeysNewLinePrinter;
import blockchain.printers.OrderedPrinter;
import blockchain.printers.Printer;
import blockchain.util.Pair;

import java.util.Optional;

public class Miner implements Runnable {
    private final BlockFactory blockFactory;
    private final int id;

    public Miner(int id, BlockFactory blockFactory) {
        this.blockFactory = blockFactory;
        this.id = id;
    }

    @Override
    public void run() {
        while (blockFactory.blocksCount() < 5) {
            Optional<Pair<PrintableBlock, String>> block = blockFactory.createBlock();
            synchronized (this.getClass()) {
                block.ifPresent(b -> logBlock(b.first, b.second));
            }
        }
    }

    private void logBlock(PrintableBlock block, String message) {
        Printer<String> printer = new ChosenKeysNewLinePrinter<>(
                new OrderedPrinter("Block:\nCreated by miner # " + id, message)
        );
        block.printTo(printer);
        System.out.println(printer.flush());
    }
}
