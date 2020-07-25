import java.util.*;

public class Main {
    static Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'y');

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(lettersToAdd(s));
    }

    static int lettersToAdd(String s) {
        int lettersToAdd = 0;
        int sameInRow = 1;
        for (int i = 1; i < s.length(); i++) {
            if (isVowel(s.charAt(i)) == isVowel(s.charAt(i - 1)) && sameInRow == 2) {
                lettersToAdd++;
                sameInRow = 1;
            } else if (isVowel(s.charAt(i)) == isVowel(s.charAt(i - 1))) {
                sameInRow = 2;
            } else {
                sameInRow = 1;
            }
        }
        return lettersToAdd;
    }

    static boolean isVowel(char letter) {
        return vowels.contains(letter);
    }
}
