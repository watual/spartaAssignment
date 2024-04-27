public interface AbstractOperation<T> {
    abstract public T calculate(T num1, T num2, char oper);
    abstract public T calculate(T radius);
}
