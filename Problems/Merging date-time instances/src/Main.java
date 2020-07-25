import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.function.Function;

public class Main {
    public static LocalDateTime merge(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        Max<LocalDateTime> max = new Max<>(dateTime1, dateTime2);
        return LocalDateTime.of(
                max.of(LocalDateTime::getYear),
                max.of(LocalDateTime::getMonthValue),
                max.of(LocalDateTime::getDayOfMonth),
                max.of(LocalDateTime::getHour),
                max.of(LocalDateTime::getMinute),
                max.of(LocalDateTime::getSecond)
        );
    }

    static class Max<T> {
        private final T o1;
        private final T o2;

        Max(T o1, T o2) {
            this.o1 = o1;
            this.o2 = o2;
        }

        <V extends Comparable<V>> V of(Function<T, V> attribute) {
            V v1 = attribute.apply(o1);
            V v2 = attribute.apply(o2);
            return v1.compareTo(v2) > 0 ? v1 : v2;
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final LocalDateTime firstDateTime = LocalDateTime.parse(scanner.nextLine());
        final LocalDateTime secondDateTime = LocalDateTime.parse(scanner.nextLine());
        System.out.println(merge(firstDateTime, secondDateTime));
    }
}