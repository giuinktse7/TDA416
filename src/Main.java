import stack.BalanceParenthesis;

public class Main {

    public static void main(String[] args) {
        String expr = "(5+ {2 - 1} + [7)";
        System.out.println(BalanceParenthesis.isBalanced(expr));
    }
}
