import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        /* 연산의 결과를 저장할 수 있도록 적합한 타입의 배열을 생성합니다. */
        int[] result = new int[10];
        /* 연산의 결과가 저장된 배열의 마지막 index를 저장하는 변수를 선언 */
        int resultIndex = 0;

        Scanner sc = new Scanner(System.in);
        /* 반복문 사용 해서 연산을 반복 */
        while (true) {
            System.out.print("첫 번째 숫자를 입력하세요: ");
            // Scanner를 사용하여 양의 정수를 입력받고 적합한 타입의 변수에 저장합니다.
            int num1 = Integer.parseInt(sc.nextLine());

            System.out.print("사칙연산 기호를 입력하세요: ");
            // 사칙연산 기호를 적합한 타입으로 선언한 변수에 저장합니다.
            String oper = sc.nextLine();

            System.out.print("두 번째 숫자를 입력하세요: ");
            // Scanner를 사용하여 양의 정수를 입력받고 적합한 타입의 변수에 저장합니다.
            int num2 = Integer.parseInt(sc.nextLine());

            /* 제어문을 활용하여 위 요구사항을 만족할 수 있게 구현합니다.*/
            try {
                switch (oper) {
                    /* 연산의 결과를 배열에 저장합니다. */
                    case "+" -> result[resultIndex] = num1 + num2;
                    case "-" -> result[resultIndex] = num1 - num2;
                    case "*" -> result[resultIndex] = num1 * num2;
                    case "/" -> result[resultIndex] = num1 / num2;
                }
            } catch (ArithmeticException e) {
                System.out.println(e.getClass());
                System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
            }
            System.out.println(STR."결과: \{result[resultIndex]}");
            /* index를 증가 시킵니다. */
            resultIndex++;
            /* 연산 결과가 10개를 초과하는 경우 가장 먼저 저장된 결과를 삭제 */
            if (resultIndex > 9) {
                for (int i = 0; i < 9; i++) {
                    result[i] = result[i + 1];
                }
                resultIndex = 9;
            }

            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            /* exit을 입력 받으면 반복 종료 */
            if (sc.nextLine().equals("exit")) {
                break;
            }

        }
    }
}