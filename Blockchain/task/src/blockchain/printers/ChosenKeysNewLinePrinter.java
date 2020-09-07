package blockchain.printers;

import java.util.Set;

public class ChosenKeysNewLinePrinter<T> implements Printer<T> {
    private static final Set<String> chosenKeys = Set.of(
            "Hash of the previous block",
            "Hash of the block", "Block data"
    );

    private final Printer<T> printer;

    public ChosenKeysNewLinePrinter(Printer<T> printer) {
        this.printer = printer;
    }

    @Override
    public void put(String attribute, Object value) {
        if (chosenKeys.contains(attribute)) {
            printer.put(attribute, "\n" + value);
        } else {
            printer.put(attribute, value);
        }
    }

    @Override
    public T flush() {
        return printer.flush();
    }
}
