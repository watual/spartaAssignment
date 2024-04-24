import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Calculator {
    /* 연산 결과를 저장하는 컬렉션 타입 필드 선언 및 생성 */
    /* +연산 결과를 저장하는 컬렉션 타입 필드를 외부에서 직접 접근 하지 못하도록 수정*/
    /* +연산 결과를 저장하는 컬렉션 타입 필드가 생성자를 통해 초기화 되도록 변경 */
    private ArrayList<Integer> result;

    private Scanner sc = new Scanner(System.in);

    /* 생성자 구현 */
    public Calculator() {
        this.result = new ArrayList<>();
    }

    public Integer calculate(int num1, int num2, String oper) {
        /* 나눗셈에서 분모에 0이 들어오거나 연산자 기호가 잘 못 들어온 경우 적합한 Exception 클래스를 생성하여 throw */
        try {
            if (!oper.equals("+") && !oper.equals("-") && !oper.equals("*") && !oper.equals("/")) {
                throw new NewBadException();
            }
            /* 제어문을 활용하여 연산 */
            switch (oper) {
                /* 연산의 결과를 배열에 저장합니다. */
                case "+" -> result.add(num1 + num2);
                case "-" -> result.add(num1 - num2);
                case "*" -> result.add(num1 * num2);
                case "/" -> result.add(num1 / num2);
            }
        } catch (ArithmeticException e) {
            System.out.println(e.getClass());
            System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
        } catch (NewBadException e) {
            System.out.println(e.getMessage());
        }
        /* return 연산 결과 */
        return result.getLast();
    }

    public ArrayList<Integer> getResult() {
        return this.result;
    }

    public void setResult(ArrayList<Integer> result) {
        this.result = result;
    }

    public void removeResult() {
        this.result.removeFirst();
    }

    public void inquiryResults() {
        /* “inquiry”라는 문자열이 입력되면 저장된 연산 결과 전부를 출력 */
        if (sc.nextLine().equals("inquiry")) {
            AtomicInteger i = new AtomicInteger(1);
            this.getResult().forEach(element -> {
                System.out.println(STR."결과\{i.getAndIncrement()}: \{element}");
            });
        }
    }
}
