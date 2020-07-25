import java.time.LocalDateTime;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.HOURS;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine());
        System.out.println(
                HOURS.between(
                        LocalDateTime.of(dateTime.getYear(), 1, 1, 0, 0),
                        dateTime
                )
        );
    }
}