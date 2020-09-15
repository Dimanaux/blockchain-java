package blockchain.blocks;

import blockchain.printers.MapPrinter;
import blockchain.util.Sha256;

public class CarrierBlock implements PrintableBlock {
    private final PrintableBlock block;
    private final String author;
    private final String transactions;

    public CarrierBlock(PrintableBlock block, String author, String transactions) {
        this.block = block;
        this.author = author;
        this.transactions = transactions;
    }

    @Override
    public String hash() {
        return new Sha256(
                String.format("%s.%s.%s", block.hash(), author, transactions)
        ).toString();
    }

    @Override
    public boolean isNextFor(Block other) {
        return this.block.isNextFor(other);
    }

    @Override
    public void printTo(MapPrinter<?> printer) {
        block.printTo(printer);
        printer.put("Created by", author);
        printer.put("Block data", transactions);
        printer.put("Hash of the block", hash());
    }
}
