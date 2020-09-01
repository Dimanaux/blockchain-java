import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class MinMax<T> {
    final T min;
    final T max;
    private final Comparator<? super T> order;

    MinMax(Comparator<? super T> order, T min, T max) {
        this.min = min;
        this.max = max;
        this.order = order;
    }

    MinMax<T> apply(T value) {
        return new MinMax<>(order,
                order.compare(value, this.min) < 0 ? value : this.min,
                order.compare(this.max, value) < 0 ? value : this.max
        );
    }

    MinMax<T> combine(MinMax<T> other) {
        return other.apply(this.min).apply(this.max);
    }

    static <T> MinMax<T> empty(Comparator<? super T> order) {
        return new MinMax<>(order, null, null) {
            @Override
            MinMax<T> apply(T value) {
                return new MinMax<>(order, value, value);
            }
        };
    }

    public static <T> void findMinMax(Stream<? extends T> stream,
                                      Comparator<? super T> order,
                                      BiConsumer<? super T, ? super T> minMaxConsumer) {
        MinMax<T> minMax = stream.reduce(MinMax.empty(order), MinMax::apply, MinMax::combine);
        minMaxConsumer.accept(minMax.min, minMax.max);
    }
}
