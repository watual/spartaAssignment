public class CircleCalculator implements AbstractOperation {
    @Override
    public int calculate(int num1, int num2, String oper) {
        return 0;
    }

    public double calculate(int radius) {
        /* 원의 넓이 계산 구현 */
        return Math.PI * radius * 2;
    }
}
