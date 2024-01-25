public class Lexer {
    private final String input;
    private int position;
    

    public Lexer(String input) {
        this.input = input;
        this.position = 0;
    }

    public Token getNextToken() {
        // Fin de l'expression
        if (position >= input.length()) {
            return new Token(TokenType.EOF, "");
        }

        char currentChar = input.charAt(position);

        if (Character.isDigit(currentChar)) {
            return readNumber();
        }

        switch (currentChar) {
            case '+':
                position++;
                return new Token(TokenType.PLUS, "+");
            case '-':
                position++;
                return new Token(TokenType.MINUS, "-");
            case '*':
                position++;
                return new Token(TokenType.MULTIPLY, "*");
            case '/':
                position++;
                return new Token(TokenType.DIVIDE, "/");
            case '(':
                position++;
                return new Token(TokenType.LEFT_PAREN, "(");
            case ')':
                position++;
                return new Token(TokenType.RIGHT_PAREN, ")");
            case ' ':
                position++;
                return getNextToken();
            default:
                throw new IllegalArgumentException("Invalid character: " + currentChar);
        }
    }

    private Token readNumber() {
        StringBuilder sb = new StringBuilder();

        while (position < input.length() && Character.isDigit(input.charAt(position))) {
            sb.append(input.charAt(position));
            position++;
        }

        return new Token(TokenType.NUMBER, sb.toString());
    }

}
