package blockchain.blocks;

public class ZeroBlock implements Block {
    @Override
    public String hash() {
        return "0";
    }

    @Override
    public boolean isNextFor(Block other) {
        return false;
    }
}
