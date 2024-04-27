public class CircleCalculator<T> implements AbstractOperation<T> {
    @Override
    public T calculate(T num1, T num2, char oper) {
        return null;
    }

    public T calculate(T radius) {
        /* 원의 넓이 계산 구현 */
        Double result = Math.PI * 2 * (double) radius;
        return (T) result;
    }
}
