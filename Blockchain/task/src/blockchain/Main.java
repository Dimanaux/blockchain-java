package blockchain;

public class Main {
    public static void main(String[] args) {
        BlockChain blockChain = new BlockChain(5);
        for (int i = 0; i < 5; i++) {
            blockChain.createBlock();
        }
    }
}
