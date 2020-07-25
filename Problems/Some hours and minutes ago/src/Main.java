import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        LocalTime time = LocalTime.parse(scanner.nextLine());
        int hoursBefore = scanner.nextInt();
        int minutesBefore = scanner.nextInt();
        System.out.println(
                time.minusHours(hoursBefore)
                        .minusMinutes(minutesBefore)
        );
    }
}
