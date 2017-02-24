package stack;

import java.util.Stack;
public class BalanceParenthesis {

    public static boolean isBalanced(String expr) {
        Stack<Character> stack = new Stack<>();
        String opening = "([{";
        String closing = ")]}";

        for (int i = 0; i < expr.length(); ++i) {
            if (opening.indexOf(expr.charAt(i)) != -1)
                stack.push(expr.charAt(i));

            if (closing.indexOf(expr.charAt(i)) != -1)
                if (stack.isEmpty() ||
                        opening.indexOf(stack.pop()) != closing.indexOf(expr.charAt(i)))
                    return false;
        }

        return true;
    }
}
