import java.util.function.*;

class Operator {
    public static LongBinaryOperator binaryOperator =
            (a, b) -> {
                long product = 1;
                for (long i = a; i <= b; i++) {
                    product *= i;
                }
                return product;
            };
}
