import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;

        public TableEntry(int key, T value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }
    }

    private static class HashTable<T> {
        private final int size;
        private final TableEntry[] table;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
        }

        public boolean put(int key, T value) {
            int index = findKey(key);
            if (index == -1) {
                return false;
            } else if (table[index] != null) {
                table[index] = new TableEntry(key, table[index].getValue() + " " + value);
            } else {
                table[index] = new TableEntry<T>(key, value);
            }
            return true;
        }

        public T get(int key) {
            return (T) table[findKey(key)].getValue();
        }

        private int findKey(int key) {
            int hash = key % size;

            while (table[hash] != null && table[hash].getKey() != key) {
                hash = (hash + 1) % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }

        @Override
        public String toString() {
            StringBuilder tableStringBuilder = new StringBuilder();

            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    tableStringBuilder
                            .append(table[i].getKey())
                            .append(": ")
                            .append(table[i].getValue());
                    tableStringBuilder.append("\n");
                }
            }

            return tableStringBuilder.toString();
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        HashTable<String> table = new HashTable<>(n);
        for (int i = 0; i < n; i++) {
            int key = scanner.nextInt();
            String value = scanner.next();
            table.put(key, value);
        }
        System.out.print(table);
    }
}