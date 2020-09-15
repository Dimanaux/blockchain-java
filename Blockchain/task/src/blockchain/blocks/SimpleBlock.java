package blockchain.blocks;

import blockchain.printers.MapPrinter;
import blockchain.util.Sha256;

public class SimpleBlock implements PrintableBlock {
    private final long id;
    private final String previousBlockHash;
    private final long timestamp;

    public SimpleBlock(long id, String previousBlockHash, long timestamp) {
        this.id = id;
        this.previousBlockHash = previousBlockHash;
        this.timestamp = timestamp;
    }

    @Override
    public void printTo(MapPrinter<?> printer) {
        printer.put("Id", id);
        printer.put("Hash of the previous block", previousBlockHash);
        printer.put("Hash of the block", hash());
        printer.put("Timestamp", timestamp);
    }

    @Override
    public String hash() {
        return new Sha256(
                id + "." + previousBlockHash + "." + timestamp
        ).toString();
    }

    @Override
    public boolean isNextFor(Block other) {
        return this.previousBlockHash.equals(other.hash());
    }

    public boolean isNextFor(SimpleBlock other) {
        return this.id > other.id && this.previousBlockHash.equals(other.hash());
    }
}
