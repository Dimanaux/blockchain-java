package blockchain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockChain blockChain = new BlockChain();
        BlockFactory blockFactory = new BlockFactory(blockChain);
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        ExecutorService daemons = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; i++) {
            executorService.submit(new Miner(i, blockFactory));
        }
        for (int i = 0; i < 4; i++) {
            daemons.submit(new Messenger(blockFactory, "User #" + i));
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        daemons.shutdown();
        daemons.awaitTermination(10, TimeUnit.MILLISECONDS);
        daemons.shutdownNow();
    }
}
