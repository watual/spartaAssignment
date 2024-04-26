public class ArithmeticCalculator implements AbstractOperation {
    public int calculate(int num1, int num2, String oper) {
        /* 나눗셈에서 분모에 0이 들어오거나 연산자 기호가 잘 못 들어온 경우 적합한 Exception 클래스를 생성하여 throw */
        int result = 0;
        try {
            if (!oper.equals("+") && !oper.equals("-") && !oper.equals("*") && !oper.equals("/")) {
                throw new NewBadException();
            }
            /* 제어문을 활용하여 연산 */
            switch (oper) {
                /* 연산의 결과를 배열에 저장합니다. */
                case "+" -> result = (num1 + num2);
                case "-" -> result = (num1 - num2);
                case "*" -> result = (num1 * num2);
                case "/" -> result = (num1 / num2);
            }
        } catch (ArithmeticException e) {
            System.out.println(e.getClass());
            System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
        } catch (NewBadException e) {
            System.out.println(e.getMessage());
        }
        /* return 연산 결과 */
        return result;
    }

    @Override
    public double calculate(int radius) {
        return 0;
    }
}
