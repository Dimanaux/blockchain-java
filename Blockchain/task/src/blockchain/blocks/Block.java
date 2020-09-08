package blockchain.blocks;

public interface Block {
    String hash();

    boolean isNextFor(Block other);
}
