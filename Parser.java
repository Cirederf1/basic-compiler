public class Parser {
    private final Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.getNextToken();
    }

    // expression = term (("+" | "-") term)*
    public int parseExpression() {
        int result = parseTerm();

        while (currentToken.getType() == TokenType.PLUS || currentToken.getType() == TokenType.MINUS) {
            Token operator = currentToken;

            if (operator.getType() == TokenType.PLUS) {
                eat(TokenType.PLUS);
                result += parseTerm();
            } else if (operator.getType() == TokenType.MINUS) {
                eat(TokenType.MINUS);
                result -= parseTerm();
            }
        }

        return result;
    }

    // term = factor (("*" | "/") factor)*
    private int parseTerm() {
        int result = parseFactor();

        while (currentToken.getType() == TokenType.MULTIPLY || currentToken.getType() == TokenType.DIVIDE) {
            Token operator = currentToken;

            if (operator.getType() == TokenType.MULTIPLY) {
                eat(TokenType.MULTIPLY);
                result *= parseFactor();
            } else if (operator.getType() == TokenType.DIVIDE) {
                eat(TokenType.DIVIDE);
                result /= parseFactor();
            }
        }

        return result;
    }

    // factor = number | (expression)
    private int parseFactor() {
        Token token = currentToken;

        if (token.getType() == TokenType.NUMBER) {
            eat(TokenType.NUMBER);
            return Integer.parseInt(token.getValue());
        } else if (token.getType() == TokenType.LEFT_PAREN) {
            eat(TokenType.LEFT_PAREN);
            int result = parseExpression();
            eat(TokenType.RIGHT_PAREN);
            return result;
        } else {
            throw new IllegalArgumentException("Invalid token type: " + token.getType());
        }
    }

    private void eat(TokenType expectedType) {
        if (currentToken.getType() == expectedType) {
            currentToken = lexer.getNextToken();
        } else {
            throw new IllegalArgumentException("Expected token type: " + expectedType + ", but found: " + currentToken.getType());
        }
    }
}
