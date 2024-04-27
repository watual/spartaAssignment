import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
    public static void main(String[] args) {
        /* 연산의 결과를 저장할 수 있도록 적합한 타입의 배열을 생성합니다. */
        /* +연산 결과가 10개로 고정되지 않고 무한이 저장될 수 있도록 소스 코드를 수정 */
        /* +적합한 컬렉션 타입의 변수 선언 */
        /* +연산 결과는 Calculator 클래스의 연산 결과를 저장하는 필드에 저장 */
//        ArrayList<Integer> result = new ArrayList<>();

        /* 연산의 결과가 저장된 배열의 마지막 index를 저장하는 변수를 선언 */
        /* +연산 결과가 10개로 고정되지 않고 무한이 저장될 수 있도록 소스 코드를 수정 */
//        int resultIndex = 0;

        /* Calculator 인스턴스 생성 */
        /* +Calculator 인스턴스는 Calculator 생성자에서 생성 */
        Calculator<Double> calculator = new Calculator();
        Scanner sc = new Scanner(System.in);
        /* 반복문 사용 해서 연산을 반복 */
        while (true) {
            /* 사칙연산을 진행할지 원의 너비를 구할지 선택 구현 */
            System.out.println("계산기 선택\n1. 사칙연산\n2. 원의 너비 구하기");
            int selectCal = Integer.parseInt(sc.nextLine());
            if (selectCal == 1) {
                System.out.print("첫 번째 숫자를 입력하세요: ");
                // Scanner를 사용하여 양의 정수를 입력받고 적합한 타입의 변수에 저장합니다.
                Double num1 = Double.parseDouble(sc.nextLine());

                System.out.print("사칙연산 기호를 입력하세요: ");
                // 사칙연산 기호를 적합한 타입으로 선언한 변수에 저장합니다.
                char oper = sc.nextLine().charAt(0);

                System.out.print("두 번째 숫자를 입력하세요: ");
                // Scanner를 사용하여 양의 정수를 입력받고 적합한 타입의 변수에 저장합니다.
                Double num2 = Double.parseDouble(sc.nextLine());

                /* 연산 수행 역할은 Calculator 클래스가 담당 */
                System.out.println(STR."결과: \{calculator.calculate(num1, num2, oper)}");

                System.out.println("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제)");
                /* “remove”라는 문자열을 입력받으면 가장 먼저 저장된 결과가 삭제될 수 있도록 구현 */
                /* +Calculator 클래스에 저장된 삭제 메서드를 활용 */
                if (sc.nextLine().equals("remove")) {
                    calculator.removeResult();
                }

                /* Calculator 클래스에 저장된 연산 결과들을 조회하는 기능을 가진 메서드를 구현한 후 App 클래스의 main 메서드에 조회 메서드가 활용 */
                System.out.println("저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회)");
                calculator.inquiryResults();

                System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
                /* exit을 입력 받으면 반복 종료 */
                if (sc.nextLine().equals("exit")) {
                    break;
                }
            } else if (selectCal == 2) {
                /* 원의 넓이를 구하는 경우 반지름을 입력받아 원의 넓이를 구한 후 출력*/
                /* +원의 넓이 저장 */
                System.out.print("반지름을 입력하세요: ");
                Double radius = Double.parseDouble(sc.nextLine());

                System.out.println(calculator.calculate(radius));
                /* 저장된 원의 넓이 값들 바로 전체 조회 */
                System.out.println(calculator.getCircleArea());
            } else {
                System.out.println("올바른 선택이 아닙니다. 다시 선택해주세요.");
            }
        }
    }
}