import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        BigInteger m = scanner.nextBigInteger();
        BigInteger n = BigInteger.ONE;
        BigInteger f = BigInteger.ONE;
        while (f.compareTo(m) < 0) {
            n = n.add(BigInteger.ONE);
            f = f.multiply(n);
        }
        System.out.println(n);
    }
}
