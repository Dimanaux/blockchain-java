import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boolean shouldBeStar = i == j || i + j + 1 == size
                        || 2 * i + 1 == size
                        || 2 * j + 1 == size;
                System.out.print(shouldBeStar ? "* " : ". ");
            }
            System.out.println();
        }
    }
}
