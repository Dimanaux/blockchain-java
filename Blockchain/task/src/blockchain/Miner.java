package blockchain;

public class Miner implements Runnable {
    private final BlockFactory blockFactory;
    private final int id;

    public Miner(int id, BlockFactory blockFactory) {
        this.blockFactory = blockFactory;
        this.id = id;
    }

    @Override
    public void run() {
        while (blockFactory.blocksCount() < 5) {
            blockFactory.createBlock(
                    "Created by miner # " + id
            );
        }
    }
}
