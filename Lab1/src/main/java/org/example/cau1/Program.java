package org.example.cau1;

public class Program {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Invalid expression.");
            return;
        }

        try {
            double result = Double.parseDouble(args[0]);

            for (int i = 1; i < args.length; i += 2) {
                if (i + 1 < args.length) {
                    String operator = args[i];
                    double num = Double.parseDouble(args[i + 1]);

                    switch (operator) {
                        case "+":
                            result += num;
                            break;
                        case "-":
                            result -= num;
                            break;
                        case "x":
                            result *= num;
                            break;
                        case "/":
                            result /= num;
                            break;
                        case "^":
                            result = Math.pow(result, num);
                            break;
                        default:
                            System.out.println("Unsupported operator: " + operator);
                            return;
                    }
                } else {
                    System.out.println("Invalid expression.");
                    return;
                }
            }

            System.out.println("Result: " + result);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter valid numeric values.");
        }
    }
}
