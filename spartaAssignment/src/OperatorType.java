public enum OperatorType {
    SETTING,
    NOTHING,
    ADD,
    SUBTRACT,
    MULTIPLAY,
    DIVIDE,
    MOD;

    public OperatorType setType(char oper){
        OperatorType result = OperatorType.SETTING;
        switch (oper){
            case '+' -> result = OperatorType.ADD;
            case '-' -> result = OperatorType.SUBTRACT;
            case '*' -> result = OperatorType.MULTIPLAY;
            case '/' -> result = OperatorType.DIVIDE;
            case '%' -> result = OperatorType.MOD;
        }
        return result;
    }
}
