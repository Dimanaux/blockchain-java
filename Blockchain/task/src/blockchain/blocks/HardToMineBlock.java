package blockchain.blocks;

import blockchain.util.Sha256;
import blockchain.printers.MapPrinter;

import java.util.Optional;
import java.util.function.Supplier;

public class HardToMineBlock implements PrintableBlock {
    private final PrintableBlock block;
    private final int security;
    private final long magicNumber;

    public static Optional<HardToMineBlock> create(
            PrintableBlock block,
            int security,
            long magicNumber
    ) {
        HardToMineBlock hardToMineBlock = new HardToMineBlock(
                block, security, magicNumber
        );
        return Optional.of(hardToMineBlock)
                .filter(HardToMineBlock::isValid);
    }

    public static HardToMineBlock mine(
            PrintableBlock block,
            int security,
            Supplier<Long> magicNumberSupplier
    ) {
        Optional<HardToMineBlock> candidate;
        do {
            candidate = create(block, security, magicNumberSupplier.get());
        } while (!candidate.isPresent());
        return candidate.get();
    }

    private HardToMineBlock(
            PrintableBlock block,
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
    public boolean isNextFor(Block other) {
        return isValid() && this.block.isNextFor(other);
    }

    @Override
    public void printTo(MapPrinter<?> printer) {
        block.printTo(printer);
        printer.put("Magic number", magicNumber);
        printer.put("Hash of the block", hash());
    }

    private boolean isValid() {
        String hash = hash();
        for (int i = 0; i < security; i++) {
            if (hash.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }
}
