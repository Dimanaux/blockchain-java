import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

class Main {
    public static void main(String[] args) {
        String s = new Scanner(System.in).nextLine();
        Optional<String> longest = Stream.of(s.split(" ")).max(comparing(String::length));
        longest.ifPresent(System.out::println);
    }
}
