package blockchain.printers;

import java.util.List;

import static java.util.Arrays.asList;

public class ChosenKeysNewLinePrinter<T> implements Printer<T> {
    private static final List<String> chosenKeys = asList(
            "Hash of the previous block",
            "Hash of the block"
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
