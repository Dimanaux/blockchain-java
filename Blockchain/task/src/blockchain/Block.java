package blockchain;

public class Block {
    private final long id;
    private final long timestamp;
    private final String previousBlockHash;
    private final long magicNumber;

    public Block(
            long id, long timestamp,
            String previousBlockHash,
            long magicNumber
    ) {
        this.id = id;
        this.timestamp = timestamp;
        this.previousBlockHash = previousBlockHash;
        this.magicNumber = magicNumber;
    }

    public boolean isPreviousOf(Block other) {
        return other.previousBlockHash.equals(hash());
    }

    public String hash() {
        return new Sha256(hashSource()).toString();
    }

    private String hashSource() {
        return String.format(
                "%s.%s.%s.%s",
                id, timestamp, previousBlockHash, magicNumber
        );
    }

    @Override
    public String toString() {
        return String.format(
                "Block:\nId: %d\nTimestamp: %d\n" +
                        "Magic number: %d\n" +
                        "Hash of the previous block: \n%s\n" +
                        "Hash of the block: \n%s",
                id, timestamp, magicNumber, previousBlockHash, hash()
        );
    }
}
