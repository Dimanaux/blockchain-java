import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        int month = scanner.nextInt();
        LocalDate monthStart = LocalDate.of(year, month, 1);
        for (int i = 0; i < monthStart.lengthOfMonth(); i++) {
            LocalDate day = monthStart.plusDays(i);
            if (day.getDayOfWeek() == DayOfWeek.MONDAY) {
                System.out.println(day);
            }
        }
    }
}
