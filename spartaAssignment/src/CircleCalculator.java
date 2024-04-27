public class CircleCalculator implements AbstractOperation {
    @Override
    public Double calculate(Double num1, Double num2, char oper) {
        return (double) 0;
    }

    public Double calculate(Double radius) {
        /* 원의 넓이 계산 구현 */
        return Math.PI * radius * 2;
    }
}
