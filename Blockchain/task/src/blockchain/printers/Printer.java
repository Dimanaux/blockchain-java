package blockchain.printers;

public interface Printer<T> {
    void put(String attribute, Object value);

    T flush();
}
