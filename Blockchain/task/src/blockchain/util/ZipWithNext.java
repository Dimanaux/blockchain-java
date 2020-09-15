package blockchain.util;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Iterator;

public class ZipWithNext<E> implements Iterable<Pair<E, E>> {
    private final Iterator<E> elements;

    public ZipWithNext(Iterable<E> collection) {
        this.elements = collection.iterator();
    }

    @NotNull
    @Override
    public Iterator<Pair<E, E>> iterator() {
        if (!elements.hasNext()) {
            return Collections.emptyIterator();
        }
        return new Iterator<>() {
            E previous = elements.next();

            @Override
            public boolean hasNext() {
                return elements.hasNext();
            }

            @Override
            public Pair<E, E> next() {
                E current = elements.next();
                Pair<E, E> pair = new Pair<>(previous, current);
                previous = current;
                return pair;
            }
        };
    }
}
