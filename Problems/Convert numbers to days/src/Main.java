import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        while (scanner.hasNext()) {
            int day = scanner.nextInt();
            System.out.println(LocalDate.ofYearDay(year, day));
        }
    }
}
