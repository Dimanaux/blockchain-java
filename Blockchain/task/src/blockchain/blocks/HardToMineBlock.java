package blockchain.blocks;

import blockchain.Sha256;
import blockchain.printers.Printable;
import blockchain.printers.Printer;

import java.util.Optional;

public class HardToMineBlock implements Block, Printable {
    private final SimpleBlock block;
    private final int security;
    private final long magicNumber;

    public static Optional<HardToMineBlock> create(
            SimpleBlock block,
            int security,
            long magicNumber
    ) {
        HardToMineBlock hardToMineBlock = new HardToMineBlock(
                block, security, magicNumber
        );
        return Optional.of(hardToMineBlock)
                .filter(HardToMineBlock::isValid);
    }

    private HardToMineBlock(
            SimpleBlock block,
            int security,
            long magicNumber
    ) {
        this.block = block;
        this.security = security;
        this.magicNumber = magicNumber;
    }

    @Override
    public String hash() {
        return new Sha256(block.hash() + "." + magicNumber).toString();
    }

    @Override
    public boolean isPreviousOf(Block other) {
        return isValid() && this.block.isPreviousOf(other);
    }

    @Override
    public void printTo(Printer<?> printer) {
        block.printTo(printer);
        printer.put("Magic number", magicNumber);
    }

    private boolean isValid() {
        return hash().startsWith("0".repeat(security));
    }
}
