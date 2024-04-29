package compiler;

public class Botresponse {
	public static String getBotResponse(String userMessage) {
		// Simple responses based on user input
	    switch (userMessage.toLowerCase()) {
	    	case "hi":
	    		return "Hi there! How can I help you?";
	        case "how are you":
	            return "I'm just a computer program, but thanks for asking!";
	        case "bye":
	            return "Goodbye! Take care.";
	        default:
	            return "Please choose your favorite tester :";
	    }
	}
}
