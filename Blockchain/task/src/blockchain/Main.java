package blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockChain blockChain = new BlockChain();
        BlockFactory blockFactory = new BlockFactory(blockChain);
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        List<Miner> miners = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            miners.add(new Miner(i, blockFactory));
        }

        for (int i = 0; i < 2; i++) {
            executorService.submit(miners.get(i)::sendMoney);
        }

        for (int i = 0; i < 3; i++) {
            executorService.submit(miners.get(i)::mine);
        }

        executorService.shutdown();
        executorService.awaitTermination(13, TimeUnit.SECONDS);
    }
}
