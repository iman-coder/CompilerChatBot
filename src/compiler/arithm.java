//package TC;
//import java.util.Scanner;
//
//public class test {
//
//	  private static int index;
//	    private static String input;
//
//	    public static void main(String[] args) {
//	        Scanner scanner = new Scanner(System.in);
//	        System.out.print("Entrez une expression math�matique : ");
//	        input = scanner.nextLine();
//
//	        try {
//	            index = 0;
//	            expression();
//	            if (index == input.length()) {
//	                System.out.println("L'expression est syntaxiquement correcte !");
//	            } else {
//	                System.out.println("Erreur syntaxique � la position " + index);
//	            }
//	        } catch (Exception e) {
//	            System.out.println("Erreur : " + e.getMessage());
//	        }
//	    }
//
//	    private static void expression() {
//	        terme();
//	        while (index < input.length() && (input.charAt(index) == '+' || input.charAt(index) == '-')) {
//	            index++;
//	            terme();
//	        }
//
//	        // Ajout de l'exponentiation
//	        if (index < input.length() && input.charAt(index) == '^') {
//	            index++;
//	            terme();
//	        }
//	    }
//
//	    private static void terme() {
//	        facteur();
//	        while (index < input.length() && (input.charAt(index) == '*' || input.charAt(index) == '/')) {
//	            index++;
//	            facteur();
//	        }
//	    }
//
//	    private static void facteur() {
//	        if (index < input.length() && input.charAt(index) == '(') {
//	            index++;
//	            expression();
//	            if (index < input.length() && input.charAt(index) == ')') {
//	                index++;
//	            } else {
//	                throw new RuntimeException("Parenth�se fermante manquante");
//	            }
//	        } else if (index < input.length() && (input.charAt(index) == '+' || input.charAt(index) == '-')) {
//	            index++;
//	            facteur(); // Permet d'avoir des termes n�gatifs, ex: -3
//	        } else {
//	            nombre();
//	        }
//	    }
//
//	    private static void nombre() {
//	        int start = index;
//	        while (index < input.length() && (Character.isDigit(input.charAt(index)) || input.charAt(index) == '.')) {
//	            index++;
//	        }
//	        if (start == index) {
//	            throw new RuntimeException("Nombre attendu � la position " + index);
//	        }
//	    }
//	
//	
//}


package compiler;
//import java.util.Scanner;

public class arithm {

	 private static int index;
	    private static String input;
	    
	    public static String getBotResponse(String userMessage) {
			// Simple responses based on user input
	    	//String_ver processor1 = new String_ver(userMessage);
	    	try {
	    		input=userMessage;
	            index = 0;
	            expression();
	            if (index == input.length()) {
	                return "The expression is syntatically correct !";
	            } else {
	                return "ERROR!! on position " + index;
	            }
	        } catch (Exception e) {
	            return "Erreur : " + e.getMessage();
	        }
		    //return "Input 1 is a valid string: " + processor1.process();
		}

//	    public static void main(String[] args) {
//	        Scanner scanner = new Scanner(System.in);
//	        System.out.print("Entrez une expression math�matique : ");
//	        input = scanner.nextLine();
//
//	        try {
//	            index = 0;
//	            expression();
//	            if (index == input.length()) {
//	                System.out.println("L'expression est syntaxiquement correcte !");
//	            } else {
//	                System.out.println("Erreur syntaxique � la position " + index);
//	            }
//	        } catch (Exception e) {
//	            System.out.println("Erreur : " + e.getMessage());
//	        }
//	    }

	    private static void expression() {
	        terme();
	        while (index < input.length() && (input.charAt(index) == '+' || input.charAt(index) == '-')) {
	            index++;
	            terme();
	        }

	        // Ajout de l'exponentiation
	        if (index < input.length() && input.charAt(index) == '^') {
	            index++;
	            terme();
	        }
	    }

	    private static void terme() {
	        facteur();
	        while (index < input.length() && (input.charAt(index) == '*' || input.charAt(index) == '/')) {
	            index++;
	            facteur();
	        }
	    }

	    private static void facteur() {
	        if (index < input.length() && input.charAt(index) == '(') {
	            index++;
	            expression();
	            if (index < input.length() && input.charAt(index) == ')') {
	                index++;
	            } else {
	                throw new RuntimeException("Parenth�se fermante manquante");
	            }
	        } else if (index < input.length() && (input.charAt(index) == '+' || input.charAt(index) == '-')) {
	            index++;
	            facteur(); // Permet d'avoir des termes n�gatifs, ex: -3
	        } else {
	            nombre();
	        }
	    }

	    private static void nombre() {
	        int start = index;
	        while (index < input.length() && (Character.isDigit(input.charAt(index)) || input.charAt(index) == '.')) {
	            index++;
	        }
	        if (start == index) {
	            throw new RuntimeException("Nombre attendu � la position " + index);
	        }
	    }
	
	
}
