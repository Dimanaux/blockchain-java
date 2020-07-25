package blockchain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter how many zeros the hash must start with: ");
        int security = scanner.nextInt();

        BlockChain blockChain = new BlockChain(security);
        for (int i = 0; i < 5; i++) {
            blockChain.createBlock();
        }
    }
}
