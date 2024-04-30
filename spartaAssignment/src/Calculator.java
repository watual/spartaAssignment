import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Calculator<T extends Number> {
    /* 연산 결과를 저장하는 컬렉션 타입 필드 선언 및 생성 */
    /* +연산 결과를 저장하는 컬렉션 타입 필드를 외부에서 직접 접근 하지 못하도록 수정*/
    /* +연산 결과를 저장하는 컬렉션 타입 필드가 생성자를 통해 초기화 되도록 변경 */
    private ArrayList<T> result;

    private Scanner sc = new Scanner(System.in);

    /* static, final 활용 */
    /* 원의 넓이 결과를 저장하는 컬렉션 타입의 필드 선언 및 생성 */
    private ArrayList<T> circleArea;

    private AbstractOperation<T> operation;
//    private AbstractOperation operation1 = new ArithmeticCalculator();
//    private AbstractOperation operation2 = new CircleCalculator();

    /* 생성자 구현 */
    public Calculator() {
        this.result = new ArrayList<>();
        this.circleArea = new ArrayList<>();
    }
//    public void setOperation(AbstractOperation operation){
//        this.operation = operation;
//    }
    //calculate 를 유지시키면서 calculate 매개변수에 따라 맞는 클래스 호출
    public T calculate(T num1, T num2, char oper){
        operation = new ArithmeticCalculator<T>();
        this.result.add( operation.calculate(num1,num2,oper) );
        return this.result.getLast();
    }
    public T calculate(T radius){
        operation = new CircleCalculator<T>();
        this.circleArea.add( operation.calculate(radius) );
        return circleArea.getLast();
    }

    public ArrayList<T> getResult() {
        return this.result;
    }

    public void setResult(ArrayList<T> result) {
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
    public void printUpperResult(){
        System.out.println("출력하고 싶은 최저연산결과 값을 입력해주세요: ");
        double i = Double.parseDouble(sc.nextLine());

        result.forEach( t -> {
            if( (double)t > i){
                System.out.println(STR."결과: \{t}");
            }
        });
    }

    /* 원의 넓이를 구하는 메서드 선언*/

    public ArrayList<T> getCircleArea() {
        return circleArea;
    }
    public void setCircleArea(ArrayList<T> newCircleArea){
        circleArea = newCircleArea;
    }

}

/*
mian 메서드
Calculator calculate 사용
내부 사칙연산과 원넓이연산 메서드를 클래스로 분리 :
====================
#1 원본
Calculator 클래스

int calculate(int,int,str) : 사칙연산

double calculate(int) : 원의 넓이
====================
#2 분리1(추상 미사용)
Calculator 클래스

ArithmeticCalculator
int calculate(int,int,str) : 사칙연산

CircleCalculator
double calculate(int) : 원너비
====================
#3 분리2 (추상 사용)
Calculator 클래스

추상 Arithmetic or Circle

ArithmeticCalculator
int calculate(int,int,str) : 사칙연산
double calculate(int) : null

CircleCalculator
int calculate(int,int,str) : null
double calculate(int) : 원너비
====================
#3 분리3 (추상 사용)
Calculator 클래스

추상 Arithmetic or Circle

ArithmeticCalculator
double calculate(int,int,str) : 사칙연산

CircleCalculator
double calculate(int,int,str) : 원너비
====================
 */
