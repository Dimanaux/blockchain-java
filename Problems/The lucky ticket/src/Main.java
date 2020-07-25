import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        String ticket = new Scanner(System.in).nextLine();
        System.out.println(isLucky(ticket) ? "Lucky" : "Regular");
    }

    static boolean isLucky(String ticket) {
        int[] digit = ticket.chars().map(c -> c - '0').toArray();
        return digit[0] + digit[1] + digit[2] == digit[3] + digit[4] + digit[5];
    }
}