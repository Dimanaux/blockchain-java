import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");

        Stream<Integer> intStream = Stream.of(input)
                .map(Integer::parseInt)
                .takeWhile(x -> x != 0);
        System.out.println(ordered(intStream.iterator()));
    }

    static <T extends Comparable<T>> boolean ordered(Iterator<T> array) {
        if (!array.hasNext()) {
            return true;
        }
        T previous = array.next();
        if (!array.hasNext()) {
            return true;
        }
        T current = array.next();
        int direction = sign(current.compareTo(previous));
        while (direction == 0 && array.hasNext()) {
            previous = current;
            current = array.next();
            direction = sign(current.compareTo(previous));
        }
        while (array.hasNext()) {
            previous = current;
            current = array.next();
            if (sign(current.compareTo(previous)) != direction && sign(current.compareTo(previous)) != 0) {
                return false;
            }
        }
        return true;
    }

    private static int sign(int compareTo) {
        return Integer.compare(compareTo, 0);
    }
}