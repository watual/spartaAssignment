import java.util.ArrayList;
import java.util.Scanner;

public class ArithmeticCalculator<T> implements AbstractOperation<T> {
    Scanner sc = new Scanner(System.in);
    private ArrayList<T> result = new ArrayList<T>();

    private AbstractFBOperations<T> abstractFBOperations;


    private void setAbstractFBOperations(AbstractFBOperations<T> abstractFBOperations) {
        this.abstractFBOperations = abstractFBOperations;
    }

    public T calculate(T num1, T num2, char oper) {
        /* 나눗셈에서 분모에 0이 들어오거나 연산자 기호가 잘 못 들어온 경우 적합한 Exception 클래스를 생성하여 throw */
        T result = null;
        try {
            if ((oper != '+') && (oper != '-') && (oper != '*') && (oper != '/') && (oper != '%')) {
                throw new NewBadException();
            }
            /* enum을 사용 */
            OperatorType type = OperatorType.NOTHING.setType(oper);
            /* 제어문을 활용하여 연산 */
            switch (type) {
                /* 연산의 결과를 배열에 저장합니다. */
                case ADD -> setAbstractFBOperations(new AddOperator<T>());
                case SUBTRACT -> setAbstractFBOperations(new SubtractOperator<T>());
                case MULTIPLAY -> setAbstractFBOperations(new MultiplayOperator<T>());
                case DIVIDE -> setAbstractFBOperations(new DivideOperator<T>());
                case MOD -> setAbstractFBOperations(new ModOperator<T>());

            }
            result = abstractFBOperations.operate(num1, num2);
        } catch (ArithmeticException e) {
            System.out.println(e.getClass());
            System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
        } catch (NewBadException e) {
            System.out.println(e.getMessage());
        }
        this.result.add(result);
        /* return 연산 결과 */
        return result;
    }

    @Override
    public T calculate(T radius) {
        return null;
    }

}
