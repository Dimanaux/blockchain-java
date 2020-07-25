import java.util.Scanner;

import static java.lang.Math.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        // |a| * |b| * cos f = a.x * b.x + a.y * b.y
        // cos f = (a.x * b.x + a.y * b.y) / (|a| * |b|)
        // f = acos((a.x * b.x + a.y * b.y) / (|a| * |b|))
        Scanner scanner = new Scanner(System.in);
        double ax = scanner.nextDouble();
        double ay = scanner.nextDouble();
        double bx = scanner.nextDouble();
        double by = scanner.nextDouble();
        double dotProduct = ax * bx + ay * by;
        double aLength = hypot(ax, ay);
        double bLength = hypot(bx, by);
        double f = acos(dotProduct / (aLength * bLength));
        System.out.println(toDegrees(f));
    }
}