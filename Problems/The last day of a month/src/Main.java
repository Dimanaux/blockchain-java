import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        int dayInYear = scanner.nextInt();
        LocalDate date = LocalDate.ofYearDay(year, dayInYear + 1);
        System.out.println(date.getDayOfMonth() == 1);
    }
}
