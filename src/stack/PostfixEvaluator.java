package stack;

import java.util.EmptyStackException;
import java.util.Stack;

public class PostfixEvaluator {

    public static class SyntaxErrorException extends Exception {
        SyntaxErrorException(String message) {
            super(message);
        }
    }

    private static final String OPERATORS = "+-*/";

    private Stack<Integer> operandStack;

    private int evaluateOperation(char operation) {
        int right = operandStack.pop();
        int left = operandStack.pop();
        int result = 0;
        switch (operation) {
            case '+':
                result = left + right;
            case '-':
                result = left - right;
            case '*':
                result = left * right;
            case '/':
                result = left / right;
        }

        return result;
    }

    private boolean isOperator(char c) {
        return OPERATORS.indexOf(c) != -1;
    }

    public int evaluate(String expression) throws SyntaxErrorException {
        operandStack = new Stack<>();

        String[] tokens = expression.split("\\s+");
        try {
            for (String token : tokens) {
                char firstChar = token.charAt(0);

                if (Character.isDefined(firstChar))
                    operandStack.push(Integer.parseInt(token));
                else if (isOperator(firstChar))
                    operandStack.push(evaluateOperation(firstChar));
                else
                    throw new SyntaxErrorException("Invalid character encountered: " + firstChar);
            }

            int answer = operandStack.pop();
            if (operandStack.empty())
                return answer;
            else
                throw new SyntaxErrorException("Syntax Error: Stack should be empty.");
        } catch (EmptyStackException e) {
            throw new SyntaxErrorException("Syntax Error: The stack is empty");
        }
    }
}
