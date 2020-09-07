package blockchain.blocks;

import blockchain.Sha256;
import blockchain.printers.Printer;

public class CarrierBlock implements PrintableBlock {
    private final PrintableBlock block;
    private final String message;

    public CarrierBlock(PrintableBlock block, String message) {
        this.block = block;
        this.message = message;
    }

    @Override
    public String hash() {
        return new Sha256(block.hash() + "." + message).toString();
    }

    @Override
    public boolean isPreviousOf(Block other) {
        return this.block.isPreviousOf(other);
    }

    @Override
    public void printTo(Printer<?> printer) {
        block.printTo(printer);
        printer.put("Block data", message);
    }
}
