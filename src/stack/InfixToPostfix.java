package stack;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class InfixToPostfix {

    private Stack<Character> operatorStack;
    private static final String OPERATORS = "+-*/()";
    private static final int[] PRECEDENCE = {1, 1, 2, 2, -1, -1};
    private StringBuilder postfix;

    public String convert(String infix) throws PostfixEvaluator.SyntaxErrorException {
        operatorStack = new Stack<>();
        postfix = new StringBuilder();

        try {
            String token;
            Scanner scanner = new Scanner(infix);
            while ((token = scanner.findInLine("[\\p{L}\\p{N}]+|[-+/*()]")) != null) {
                char firstChar = token.charAt(0);

                //Operand?
                if (Character.isJavaIdentifierStart(firstChar)
                    || Character.isDigit(firstChar)) {
                    postfix.append(token);
                    postfix.append(' ');
                }
                //Operator?
                else if (isOperator(firstChar))
                    processOperator(firstChar);
                else
                    throw new PostfixEvaluator.SyntaxErrorException("Unexpected character encountered: " + firstChar);
            }

            while(!operatorStack.empty()) {
                char op = operatorStack.pop();
                if (op == '(')
                    throw new PostfixEvaluator.SyntaxErrorException("Unmatched opening parenthesis.");
                postfix.append(op);
                postfix.append(' ');
            }

            //assert: Stack is empty, return result.
            return postfix.toString();
        } catch (EmptyStackException e) {
            throw new PostfixEvaluator.SyntaxErrorException("Syntax error: The stack is empty.");
        }
    }

    private void processOperator(char operation) {
        if (operatorStack.empty() || operation == '(')
            operatorStack.push(operation);
        else {
            char topOperation = operatorStack.peek();
            if (precedence(operation) > precedence(topOperation))
                operatorStack.push(operation);
            else {
                //Pop all stacked operators with equal or higher precedence than operation.
                while (!operatorStack.empty() && precedence(operation) <= precedence(topOperation)) {
                    operatorStack.pop();
                    if (topOperation == '(')
                        //Matching '(' popped - exit loop
                        break;
                    postfix.append(topOperation);
                    postfix.append(' ');
                    if (!operatorStack.empty())
                        //Reset topOperation
                        topOperation = operatorStack.peek();
                }

                //assert: Operator stack is empty or current operator precedence > top of stack operator precedence.
                if (operation != ')')
                    operatorStack.push(operation);
            }
        }
    }

    private int precedence(char operation) {
        return PRECEDENCE[OPERATORS.indexOf(operation)];
    }

    private boolean isOperator(char c) {
        return OPERATORS.indexOf(c) != -1;
    }
}
