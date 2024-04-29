package compiler;

//import java.util.Scanner;

public class String_ver {
	

	    private String input;
	    private int index;

	    public String_ver(String input) {
	        this.input = input;
	        this.index = 0;
	    }

	    public boolean process() {
	        return stringRule();
	    }

//	    stringRule ::= '"' characters '"'
//	    characters ::= any_sequence_of_characters_except_double_quotes

	    
	    private boolean stringRule() {
	        if (index < input.length() && input.charAt(index) == '"') {
	            index++;
	            while (index < input.length() && input.charAt(index) != '"') {
	                index++;
	            }
	            return index < input.length() && input.charAt(index) == '"';
	        }
	        return false;
	    }
	    
	    public static String getBotResponse(String userMessage) {
			// Simple responses based on user input
	    	String_ver processor1 = new String_ver(userMessage);
	    	if(processor1.process()) {
	    		return "Input 1 is a valid string";
	    	}
		    return "Input 1 is not a valid string";
		}

//	    public static void main(String[] args) {
//	        String input1 = null;
//	        String input2 = null;
//
//	        Scanner scanner = new Scanner(System.in);
//	        
//	        System.out.print("Entrez input 1 : ");
//	        input1 = scanner.nextLine();
//	        
//	        System.out.print("Entrez input 2 : ");
//	        input2 = scanner.nextLine();
//	        
//	        String_ver processor1 = new String_ver(input1);
//	        System.out.println("Input 1 is a valid string: " + processor1.process());
//
//	        String_ver processor2 = new String_ver(input2);
//	        System.out.println("Input 2 is a valid string: " + processor2.process());
//	    }
	}


