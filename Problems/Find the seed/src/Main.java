import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import static java.util.stream.IntStream.generate;
import static java.util.stream.IntStream.rangeClosed;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        Map.Entry<Integer, Integer> min = rangeClosed(a, b)
                .mapToObj(seed -> {
                    Random random = new Random(seed);
                    int max = generate(() -> random.nextInt(k))
                            .limit(n).max().getAsInt();
                    return Map.entry(seed, max);
                }).min(Map.Entry.comparingByValue()).get();

        System.out.println(min.getKey());
        System.out.println(min.getValue());
    }
}