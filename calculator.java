import java.util.Stack;

public class calculator {

    private Stack<Double> operandStack; // Stack to store operands
    private Stack<Character> operatorStack; // Stack to store operators

    public calculator() {
        this.operandStack = new Stack<>();
        this.operatorStack = new Stack<>();
    }

    // Method to evaluate an expression
    public double evaluateExpression(String expression) {
        try {
            operandStack.clear(); // Clear the operand stack before evaluating a new expression
            operatorStack.clear(); // Clear the operator stack before evaluating a new expression

            // Iterate through the expression
            for (int i = 0; i < expression.length(); i++) {
                char ch = expression.charAt(i);
                if (ch == ' ') {
                    continue; // Skip whitespace characters
                } else if (Character.isDigit(ch)) {

                    StringBuilder num = new StringBuilder();
                    while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                        num.append(expression.charAt(i++));
                    }
                    operandStack.push(Double.parseDouble(num.toString()));
                    i--;
                } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    // If the character is an operator, perform operations based on operator precedence
                    while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(ch)) {
                        applyOperation();
                    }
                    operatorStack.push(ch);
                } else if (ch == '(') {
                    // If the character is an opening parenthesis, push it onto the operator stack
                    operatorStack.push(ch);
                } else if (ch == ')') {
                    // If the character is a closing parenthesis, perform operations until matching opening parenthesis is found
                    while (operatorStack.peek() != '(') {
                        applyOperation();
                    }
                    operatorStack.pop(); // Discard the matching opening parenthesis
                }
            }

            // Perform remaining operations
            while (!operatorStack.isEmpty()) {
                applyOperation();
            }

            // The final result is the only element left in the operand stack
            return operandStack.pop();
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN; // Error occurred during evaluation
        }
    }

    // Method to determine operator precedence
    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    // Method to apply an operation based on the top operator in the operator stack
    private void applyOperation() {
        char operator = operatorStack.pop();
        double operand2 = operandStack.pop();
        double operand1 = operandStack.pop();
        double result = 0;
        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                result = operand1 / operand2;
                break;
        }
        operandStack.push(result);
    }
}
