import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDateTime from = LocalDateTime.parse(scanner.nextLine());
        LocalDateTime to = LocalDateTime.parse(scanner.nextLine());

        if (to.isBefore(from)) {
            LocalDateTime swap = to;
            to = from;
            from = swap;
        }

        int datesCount = Integer.parseInt(scanner.nextLine());
        int between = 0;
        for (int i = 0; i < datesCount; i++) {
            LocalDateTime date = LocalDateTime.parse(scanner.nextLine());
            if (from.equals(date) || from.isBefore(date) && date.isBefore(to)) {
                between++;
            }
        }
        System.out.println(between);
    }
}
