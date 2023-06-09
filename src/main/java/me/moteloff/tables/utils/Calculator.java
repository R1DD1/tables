package me.moteloff.tables.utils;

import me.moteloff.tables.exeptions.ExpressionException;

import java.util.Stack;

public class Calculator {
    public static String evaluate(String expression) throws ExpressionException {
        if (!expression.startsWith("=")) return expression;

        Stack<Integer> operands = new Stack<Integer>();
        Stack<Character> operators = new Stack<Character>();

        parseExpression(expression, operands, operators);

        applyOperators(operands, operators);

        return String.valueOf(evaluateExpression(operands));
    }

    private static void parseExpression(String expression, Stack<Integer> operands, Stack<Character> operators) {
        expression = expression.substring(1);

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == ' ') {
                continue;
            } else if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    sb.append(expression.charAt(i++));
                }
                i--;
                operands.push(Integer.parseInt(sb.toString()));
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    int result = applyOperation(operators.pop(), operands.pop(), operands.pop());
                    operands.push(result);
                }
                operators.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operators.isEmpty() && hasPrecedence(c, operators.peek())) {
                    int result = applyOperation(operators.pop(), operands.pop(), operands.pop());
                    operands.push(result);
                }
                operators.push(c);
            }
        }
    }

    private static void applyOperators(Stack<Integer> operands, Stack<Character> operators) {
        while (!operators.isEmpty()) {
            int result = applyOperation(operators.pop(), operands.pop(), operands.pop());
            operands.push(result);
        }
    }

    private static int evaluateExpression(Stack<Integer> operands) {
        return operands.pop();
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
    }

    private static int applyOperation(char operator, int b, int a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }
}
