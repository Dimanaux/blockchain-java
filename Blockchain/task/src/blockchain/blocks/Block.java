package blockchain.blocks;

public interface Block {
    String hash();

    boolean isPreviousOf(Block other);
}
