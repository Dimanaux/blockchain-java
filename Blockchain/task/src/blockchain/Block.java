package blockchain;

public class Block {
    private final long id;
    private final long timestamp;
    private final String previousBlockHash;
    private final long magicNumber;
    private final String payload;

    public Block(
            long id, long timestamp,
            String previousBlockHash,
            long magicNumber,
            String payload
    ) {
        this.id = id;
        this.timestamp = timestamp;
        this.previousBlockHash = previousBlockHash;
        this.magicNumber = magicNumber;
        this.payload = payload;
    }

    public boolean isPreviousOf(Block other) {
        return other.previousBlockHash.equals(hash());
    }

    public String hash() {
        return new Sha256(hashSource()).toString();
    }

    private String hashSource() {
        return String.format(
                "%s.%s.%s.%s.%s",
                id, timestamp, previousBlockHash,
                magicNumber, payload
        );
    }

    @Override
    public String toString() {
        return String.format(
                "Block:\n%s\n" +
                        "Id: %d\n" +
                        "Timestamp: %d\n" +
                        "Magic number: %d\n" +
                        "Hash of the previous block: \n%s\n" +
                        "Hash of the block: \n%s",
                payload, id, timestamp, magicNumber, previousBlockHash, hash()
        );
    }

    public long getTimestamp() {
        return timestamp;
    }
}
