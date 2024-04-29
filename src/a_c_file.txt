public class Parser {

    // Tokenizer to tokenize the input expression
    private Tokenizer tokenizer;

    public Parser(String input) {
        this.tokenizer = new Tokenizer(input);
    }

    // Expression = SExpr [(‘=‘ | ‘>’ | ‘<’) SExpr].
    public boolean parseExpression() {
        if (parseSExpr()) {
            while (tokenizer.match(TokenType.EQUAL, TokenType.GREATER_THAN, TokenType.LESS_THAN)) {
                tokenizer.advance(); // Consume the operator
                if (!parseSExpr()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // SExpr = [‘+’ | ‘-’] Terme {(‘+’ | ‘-’ | ‘or’) Terme}.
    private boolean parseSExpr() {
        if (tokenizer.match(TokenType.PLUS, TokenType.MINUS)) {
            tokenizer.advance(); // Consume the optional '+' or '-'
        }

        if (parseTerme()) {
            while (tokenizer.match(TokenType.PLUS, TokenType.MINUS, TokenType.OR)) {
                tokenizer.advance(); // Consume the operator
                if (!parseTerme()) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    // Terme = Facteur {(‘*’ | ‘/’ | ‘and’) Facteur}.
    private boolean parseTerme() {
        if (parseFacteur()) {
            while (tokenizer.match(TokenType.MULTIPLY, TokenType.DIVIDE, TokenType.AND)) {
                tokenizer.advance(); // Consume the operator
                if (!parseFacteur()) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    // Facteur = Ident | Nombre | (‘not’ Facteur) | ‘(’ Expression ‘)’ .
    private boolean parseFacteur() {
        if (tokenizer.match(TokenType.IDENTIFIER, TokenType.NUMBER)) {
            tokenizer.advance(); // Consume identifier or number
            return true;
        } else if (tokenizer.match(TokenType.NOT)) {
            tokenizer.advance(); // Consume 'not'
            return parseFacteur();
        } else if (tokenizer.match(TokenType.OPEN_PAREN)) {
            tokenizer.advance(); // Consume '('
            if (parseExpression()) {
                return tokenizer.match(TokenType.CLOSE_PAREN); // Consume ')'
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String inputExpression = "x + 5 * (y - 3) = z";
        Parser parser = new Parser(inputExpression);

        if (parser.parseExpression() && parser.tokenizer.isAtEnd()) {
            System.out.println("Expression is valid!");
        } else {
            System.out.println("Expression is invalid!");
        }
    }
}

class Tokenizer {
    // Token types
    public enum TokenType {
        IDENTIFIER, NUMBER, PLUS, MINUS, MULTIPLY, DIVIDE, EQUAL, GREATER_THAN, LESS_THAN, AND, OR, NOT, OPEN_PAREN, CLOSE_PAREN
    }

    private String input;
    private int position;

    public Tokenizer(String input) {
        this.input = input;
        this.position = 0;
    }

    public boolean isAtEnd() {
        return position >= input.length();
    }

    public TokenType peek() {
        if (isAtEnd()) {
            return null;
        }
        char currentChar = input.charAt(position);
        switch (currentChar) {
            case '+':
                return TokenType.PLUS;
            case '-':
                return TokenType.MINUS;
            case '*':
                return TokenType.MULTIPLY;
            case '/':
                return TokenType.DIVIDE;
            case '=':
                return TokenType.EQUAL;
            case '>':
                return TokenType.GREATER_THAN;
            case '<':
                return TokenType.LESS_THAN;
            case 'a':
                return TokenType.AND;
            case 'o':
                return TokenType.OR;
            case 'n':
                return TokenType.NOT;
            case '(':
                return TokenType.OPEN_PAREN;
            case ')':
                return TokenType.CLOSE_PAREN;
            default:
                if (Character.isLetter(currentChar)) {
                    return TokenType.IDENTIFIER;
                } else if (Character.isDigit(currentChar)) {
                    return TokenType.NUMBER;
                }
                return null;
        }
    }

    public boolean match(TokenType... expectedTypes) {
        for (TokenType expectedType : expectedTypes) {
            if (match(expectedType)) {
                return true;
            }
        }
        return false;
    }

    public boolean match(TokenType expectedType) {
        if (isAtEnd()) {
            return false;
        }
        TokenType currentTokenType = peek();
        return currentTokenType != null && currentTokenType == expectedType;
    }

    public void advance() {
        if (!isAtEnd()) {
            position++;
        }
    }
}
