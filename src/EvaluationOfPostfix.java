import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class EvaluationOfPostfix {
    public BigDecimal evaluate(String str) { // Evaluating Expression
        String[] strArr = str.split(" ");
        Stack<BigDecimal> stack = new Stack<>();

        BigDecimal x, y, result;
        for (String s : strArr) {
            if (s.length() == 1 && isOperator(s.charAt(0))) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid expression");
                }
                y = stack.pop();
                x = stack.pop();
                result = opr(s.charAt(0), x, y);
                stack.push(result);
            } else {
                try {
                    BigDecimal num = new BigDecimal(s);
                    stack.push(num);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid expression: " + s);
                }
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }
        return stack.pop();
    }
    private BigDecimal opr(char ch, BigDecimal num1, BigDecimal num2) { // Arithmetic Operations
        return switch (ch) {
            case '+' -> num1.add(num2);
            case '-' -> num1.subtract(num2);
            case '*' -> num1.multiply(num2);
            case '/' -> {
                if (num2.compareTo(BigDecimal.ZERO) == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                yield num1.divide(num2, 10, RoundingMode.HALF_UP);
            }
            case '%' -> {
                if (num2.compareTo(BigDecimal.ZERO) == 0) {
                    throw new ArithmeticException("Modulus by zero");
                }
                yield num1.remainder(num2);
            }
            default -> throw new IllegalArgumentException("Unknown operator: " + ch);
        };
    }

    public String infixToPostfix(String str) { // Converting given infix to postfix
        Stack<Character> stack = new Stack<>();
        StringBuilder stb = new StringBuilder();
        int i = 0;

        while (i < str.length()) {
            char ch = str.charAt(i);

            if (isDigit(ch)) {
                i = handleIsDigit(str, stb, i);
            } else if (isParenthesis(ch)) {
                handleParenthesis(stb, stack, ch);
            } else if (isOperator(ch)) {
                handleOperator(stb, stack, ch);
            } else {
                throw new IllegalArgumentException("Invalid character in expression: " + ch);
            }
            i++;
        }

        while (!stack.isEmpty()) {
            char top = stack.pop();
            if (isParenthesis(top)) {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            stb.append(top).append(' ');
        }
        return stb.toString();
    }

    private int handleIsDigit(String str, StringBuilder stb, int i) {
        StringBuilder operand = new StringBuilder();
        while (i < str.length() && isDigit(str.charAt(i))) {
            operand.append(str.charAt(i));
            i++;
        }
        stb.append(operand).append(' ');
        return --i;
    }

    private void handleOperator(StringBuilder stb, Stack<Character> stack, char ch) {
        while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch)) {
            stb.append(stack.pop()).append(' ');
        }
        stack.push(ch);
    }

    private void handleParenthesis(StringBuilder stb, Stack<Character> stack, char ch) {
        if (ch == '(') {
            stack.push(ch);
        } else { // ch == ')'
            while (!stack.isEmpty() && stack.peek() != '(') {
                stb.append(stack.pop()).append(' ');
            }
            if (stack.isEmpty()) {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            stack.pop(); // Remove the '(' from the stack
        }
    }

    protected boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%';
    }

    protected boolean isParenthesis(char ch) {
        return ch == '(' || ch == ')';
    }

    protected boolean isDigit(char ch) {
        return ch == '.' || (ch >= '0' && ch <= '9');
    }

    private int precedence(char ch) {
        return switch (ch) {
            case '+', '-' -> 1;
            case '*', '/', '%' -> 2;
            case '(', ')' -> 0; // Parentheses precedence is considered as 0
            default -> -1;
        };
    }
}
