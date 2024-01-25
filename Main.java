import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print("Enter an expression (or 'exit' to quit): ");
            input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }

            try {
                Lexer lexer = new Lexer(input);
                Parser parser = new Parser(lexer);
                int result = parser.parseExpression();

                System.out.println("Result: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                continue;
            }
        }
    }
} 