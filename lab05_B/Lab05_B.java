import java.util.*;

public class Lab05_B {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input: ");
            String data = scanner.nextLine();

            if (data.equals("99")) {
                break;
            }

            List<String> tokens = lexicalAnalysis(data);

            ArrayList<String> syntaxErrors = syntaxAnalysis(tokens);

            ArrayList<String> semanticErrors = semanticAnalysis(tokens);
        }

        scanner.close();
    }

    private static List<String> lexicalAnalysis(String data) {
        List<String> tokensList = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(data, "+-*/", true);

        while (tokenizer.hasMoreTokens()) {
            String token[] = tokenizer.nextToken().split("");
            for (String string : token) {
                tokensList.add(string);
                System.out.println(string);
            }
        }

        return tokensList;
    }

    private static ArrayList<String> syntaxAnalysis(List<String> values) {
        ArrayList<String> errorCodes = new ArrayList<>();
    
        for (int i = 0; i < values.size(); i++) {
            String token = values.get(i);
    
            if (token.matches(".*\\d+.*")) {
                System.out.println("SYNTAX ERROR: Numbers 0 to 9 are not allowed in any part of the token. Token: " + token);
                errorCodes.add("S001");
                continue;
            }
    
            if (!token.matches("[A-Za-z+\\-*/]+")) {
                System.out.println("SYNTAX ERROR: Only letters, '+', '-', '*', and '/' are allowed. Token: " + token);
                errorCodes.add("S002");
                continue;
            }
    
            // Check if the token is an operator or an identifier
            String tokenType = token.matches("[+\\-*/]") ? "Operator" : "Identifier";
            
            // Print the token along with its type
            System.out.println("TOKEN#" + (i + 1) + " " + token + " " + tokenType);
        }
    
        System.out.println("Stage 1 Complete");
    
        return errorCodes;
    }
    

    private static ArrayList<String> semanticAnalysis(List<String> tokens) {
        ArrayList<String> errorCodes = new ArrayList<>();

        for (int i = 0; i < tokens.size() - 1; i++) {
            String currentToken = tokens.get(i);
            String nextToken = tokens.get(i + 1);

            if (isIdentifier(currentToken) && isIdentifier(nextToken)) {
                System.out.println(
                        "\nSEMANTIC ERROR- two operators (*,-,+,/) or Identifier (A to Z and a to z) cannot be written together! :::: "
                                + currentToken + ", " + nextToken);
                errorCodes.add("S100");
            }

            if (isOperator(currentToken) && isOperator(nextToken)) {
                System.out.println(
                        "\nSEMANTIC ERROR- two operators (*,-,+,/) or Identifier (A to Z and a to z) cannot be written together! :::: "
                                + currentToken + ", " + nextToken);
                errorCodes.add("S101");
            }

            if (isIdentifier(currentToken) && isIdentifier(nextToken)) {
                System.out.println(
                        "\nSEMANTIC ERROR-Invalid String! There is no operator in the String (+, /, -, *,) :::: "
                                + currentToken + ", " + nextToken);
                errorCodes.add("S102");
            }
        }

        return errorCodes;
    }

    private static boolean isOperator(String token) {
        return token.matches("[+\\-*/]");
    }

    private static boolean isIdentifier(String token) {
        return token.matches("[A-Za-z]+");
    }

}
