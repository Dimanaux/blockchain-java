package blockchain.printers;

public interface MapPrinter<T> {
    void put(String attribute, Object value);

    T flush();
}
