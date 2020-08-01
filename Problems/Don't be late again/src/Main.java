import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        var arrivalTime = LocalTime.of(20, 0);
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String store = scanner.next();
            LocalTime closeTime = LocalTime.parse(scanner.next());
            if (arrivalTime.isBefore(closeTime)) {
                System.out.println(store);
            }
        }
    }
}