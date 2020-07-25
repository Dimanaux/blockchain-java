import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import static java.util.stream.Collectors.joining;

public class Main {
    static class Random {
        int oddBit = 1;

        int nextInt(int bound) {
            oddBit = 1 - oddBit;
            return bound / 2 + oddBit;
        }
    }

    static Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int upperCaseLetters = scanner.nextInt();
        int lowerCaseLetters = scanner.nextInt();
        int digits = scanner.nextInt();
        int symbols = scanner.nextInt();
        System.out.println(generatePassword(
                upperCaseLetters, lowerCaseLetters,
                digits, symbols
        ));
    }

    static String generatePassword(int upperCaseLetters, int lowerCaseLetters,
                                   int digits, int symbols) {
        ArrayList<Character> password = new ArrayList<>(symbols);
        addRandomChars(password, 'A', 'Z', upperCaseLetters);
        addRandomChars(password, 'a', 'z', lowerCaseLetters);
        addRandomChars(password, '0', '9', digits);
        int rest = symbols - upperCaseLetters - lowerCaseLetters - digits;
        addRandomChars(password, '!', '~', rest);
        return password.stream().map(Object::toString).collect(joining(""));
    }

    static void addRandomChars(Collection<Character> characters,
                               int lowerBound, int upperBound, int count) {
        for (int i = 0; i < count; i++) {
            characters.add((char) (lowerBound
                    + random.nextInt(upperBound - lowerBound + 1)));
        }
    }
}
