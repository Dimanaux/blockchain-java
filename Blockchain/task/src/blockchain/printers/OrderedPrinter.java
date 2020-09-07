package blockchain.printers;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OrderedPrinter implements Printer<String> {
    private static final List<String> order = List.of(
            "Id", "Timestamp", "Magic number",
            "Hash of the previous block", "Hash of the block",
            "Block data"
    );

    private final HashMap<String, String> attributes = new HashMap<>();
    private final String header;
    private final String footer;

    public OrderedPrinter(String header, String footer) {
        this.header = header;
        this.footer = footer;
    }

    @Override
    public void put(String attribute, Object value) {
        attributes.put(attribute, value.toString());
    }

    @Override
    public String flush() {
        return order.stream()
                .map(key -> key + ": " + attributes.getOrDefault(key, ""))
                .collect(Collectors.joining("\n", header, footer));
    }
}
