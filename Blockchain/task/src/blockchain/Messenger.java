package blockchain;

public class Messenger implements Runnable {
    private final BlockFactory blockFactory;
    private final String name;

    public Messenger(BlockFactory blockFactory, String name) {
        this.blockFactory = blockFactory;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            sleep();
            blockFactory.sendMessage(name + ": " + message());
        }
    }

    private String message() {
        return "Hello, my name is " + name;
    }

    private static void sleep() {
        long sleepTime = (long) (Math.random() * 1000);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ignored) {
        }
    }
}
