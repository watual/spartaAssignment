public class MultiplayOperator<T> implements AbstractFBOperations<T> {

    @Override
    public T operate(T num1, T num2) {
        Double a = (double) num1 * (double) num2;
        return (T) a;
    }
}
