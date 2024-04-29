package compiler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class javaCodeGenerator {
	private static String readFile(String inputFilePath) {
		StringBuilder stringBuilder = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {

	            String line="package compiler;\n";
	            while ((line = reader.readLine()) != null) {
	                // Process the line (replace this with your processing logic)
	                // For demonstration, let's assume we're just appending the line to the string
	                stringBuilder.append(line).append("\n");
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return stringBuilder.toString();
	    }
    private static void writeToFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    private static void executeJavaProgram(String filePath) throws IOException, InterruptedException {
        // Build the command to compile and run the Java program
        ProcessBuilder processBuilder = new ProcessBuilder("javac", filePath);
        processBuilder.redirectErrorStream(true);

        // Start the compilation process
        Process compileProcess = processBuilder.start();

        // Wait for the compilation process to complete
        int compileExitCode = compileProcess.waitFor();

        // Display the compilation result
        System.out.println("Compilation exit code: " + compileExitCode);

        // If compilation is successful, execute the Java program
        if (compileExitCode == 0) {
            // Build the command to run the compiled Java program
            processBuilder.command("java", "HelloWorld");

            // Start the execution process
            Process executeProcess = processBuilder.start();

            // Wait for the execution process to complete
            int executeExitCode = executeProcess.waitFor();

            // Display the execution result
            System.out.println("Execution exit code: " + executeExitCode);
        }
    }
    public static void main(String[] args) {
        // Generate C code
    	String inputFilePath="C:\\Users\\thinkpad\\eclipse-workspace\\compiler\\src\\a_c_file.txt";
        //String cCode = "#include <stdio.h>\n\nint main() {\n    printf(\"Hello, World!\\n\");\n    return 0;\n	scanf();\n}";

        // Specify the C file path
        String cFilePath = "C:\\Users\\thinkpad\\eclipse-workspace\\compiler\\src\\C_files\\generated_c_program.java";

        try {
            // Write the C code to a file
            writeToFile(cFilePath, readFile(inputFilePath));

            // Execute the C program
            executeJavaProgram(cFilePath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}


