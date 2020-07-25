import java.util.Scanner;

class Time {

    int hour;
    int minute;
    int second;

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public static Time noon() {
        return Time.of(12, 0, 0);
    }

    public static Time midnight() {
        return Time.of(0, 0, 0);
    }

    public static Time ofSeconds(long seconds) {
        // write your code here
        int hoursComponent = (int) (seconds / 3600 % 24);
        int secondsComponent = (int) (seconds % 60);
        int minutesComponent = (int) (seconds / 60 % 60);
        return Time.of(hoursComponent, minutesComponent, secondsComponent);
    }

    public static Time of(int hour, int minute, int second) {
        // write your code here
        boolean hoursInBounds = 0 <= hour && hour < 24;
        boolean minutesInBounds = 0 <= minute && minute < 60;
        boolean secondsInBounds = 0 <= second && second < 60;
        if (!(hoursInBounds && minutesInBounds && secondsInBounds)) {
            return null;
        }
        return new Time(hour, minute, second);
    }
}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final String type = scanner.next();
        Time time = null;

        switch (type) {
            case "noon":
                time = Time.noon();
                break;
            case "midnight":
                time = Time.midnight();
                break;
            case "hms":
                int h = scanner.nextInt();
                int m = scanner.nextInt();
                int s = scanner.nextInt();
                time = Time.of(h, m, s);
                break;
            case "seconds":
                time = Time.ofSeconds(scanner.nextInt());
                break;
            default:
                time = null;
                break;
        }

        if (time == null) {
            System.out.println(time);
        } else {
            System.out.println(String.format("%s %s %s", time.hour, time.minute, time.second));
        }
    }
}