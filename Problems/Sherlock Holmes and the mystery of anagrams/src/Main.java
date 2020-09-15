import java.util.Scanner;

import static java.util.stream.Collectors.toList;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word1 = scanner.nextLine();
        String word2 = scanner.nextLine();
        System.out.println(isAnagrams(word1, word2) ? "yes" : "no");
    }

    static boolean isAnagrams(String s1, String s2) {
        return s1.toLowerCase().codePoints().sorted().boxed().collect(toList()).equals(
                s2.toLowerCase().codePoints().sorted().boxed().collect(toList())
        );
    }
}
