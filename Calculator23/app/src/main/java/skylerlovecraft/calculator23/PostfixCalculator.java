package skylerlovecraft.calculator23;

/**
 * Created by Skyler on 9/11/17.
 *
 * adapted from https://softwarecave.org/2014/04/03/evaluating-postfix-expressions/
 1.Initialize empty stack
 2.For every token in the postfix expression (scanned from left to right):
 1. If the token is an operand (number)
 1. Check if the token is prefixed by "(-)"
 2. If it is prefixed by "(-)", extract the number from the token and multiply it by negative 1
 3. Otherwise just extract the number from the token.
 4. Push the operand onto the stack.
 2. Otherwise, if the token is an operator (or function):
 1. Check if the stack contains the sufficient number of values (usually two) for given operator
 2. If there are not enough values, finish the algorithm with an error
 3. Pop the appropriate number of values from the stack
 4. Evaluate the operator using the popped values and push the single result on the stack
 3. If the stack contains only one value, return it as a final result of the calculation
 4. Otherwise, finish the algorithm with an error
 original algorithm (before my changes to the logic) outlined at: https://softwarecave.org/2014/04/03/evaluating-postfix-expressions/
 */
import java.util.Stack;
import java.util.Scanner;

public class PostfixCalculator {
    private Stack<Double> stack;
    Scanner scanner;
    public PostfixCalculator() {
        stack = new Stack();
    }

    public double evaluate(String s) {
        stack.clear();
        scanner = new Scanner(s);
        while(scanner.hasNext()){

            String tok = scanner.next();
            System.out.println(tok);
            processToken(tok);
        }
        if (stack.size() == 1)
            return stack.pop();

        else
            throw new IllegalArgumentException("Invalid number of operators");
    }
    private void processToken(String token) {
        switch (token) {
            case "+":
                addArguments();
                break;
            case "-":
                subArguments();
                break;
            case "*":
                mulArguments();
                break;
            case "/":
                divArguments();
                break;
            default:
                //Because we have made it down here, the token is either going to be invalid or a number
                //If it is a negative number it will be of the form (-)X. We will want to convert this
                //Token to the negative number by extracting the token after (-) and multiplying it by negative 1.

                if(token.charAt(0) == '('&& token.charAt(1) == '-' && token.charAt(2) == ')')
                {
                    try {
                        double arg = Double.parseDouble(token.substring(3));
                        arg *= -1;
                        stack.push(arg);
                    } catch (NumberFormatException e){
                        throw new IllegalArgumentException("Invalid number: " + token, e);
                    }
                }
                else {
                    try {
                        double arg = Double.parseDouble(token);
                        stack.push(arg);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid number: " + token, e);
                    }
                }
        }
    }

    private void addArguments() {
        checkArgumentsSize();
        double arg2 = stack.pop();
        double arg1 = stack.pop();
        stack.push(arg1 + arg2);
    }

    private void subArguments() {
        checkArgumentsSize();
        double arg2 = stack.pop();
        double arg1 = stack.pop();
        stack.push(arg1 - arg2);
    }

    private void mulArguments() {
        checkArgumentsSize();
        double arg2 = stack.pop();
        double arg1 = stack.pop();
        stack.push(arg1 * arg2);
    }

    private void divArguments() {
        checkArgumentsSize();
        double arg2 = stack.pop();
        double arg1 = stack.pop();
        stack.push(arg1 / arg2);
    }

    private void checkArgumentsSize() {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("Not enough parameters for operation");
        }
    }
}

