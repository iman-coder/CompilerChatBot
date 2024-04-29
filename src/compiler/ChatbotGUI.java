package compiler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatbotGUI extends JFrame {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea conversationArea;
	private JTextField userInputField;
	private JPanel infoPanel;
	private Boolean programJustLaunched=true;
	private static Boolean strings=false;
	private static Boolean arith=false;

	public ChatbotGUI() {
	        // Set up the JFrame
	        setTitle("Compiler");
	        setSize(550, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	        // Create components
	        conversationArea = new JTextArea();
	        conversationArea.setEditable(false);
	        conversationArea.setLineWrap(true);

	        userInputField = new JTextField();
	        JButton sendButton = new JButton("Send");
	        
	        // Check if the program has just launched
	        if (programJustLaunched) {
	        	conversationArea.append("Hi i'm a compiler and i'm here to verify if your code is correct!!\nplease choose your favorite tester :\n");
	        	//System.out.println("Before conditionals: arith=" + arith + ", strings=" + strings);
	            programJustLaunched = false;
	            if (arith && strings==false) {
	                // Access file for arithmetic processing
	            	conversationArea.append("Entrez une expression math�matique :\n");
	            	System.out.println("Inside arith conditional");
	            } else if (strings && arith==false) {
	                // Access file for string processing
	            	conversationArea.append("Entrez une chaine de caracteres :\n");
	            	System.out.println("Inside string conditional");
	            }
	        }
	        // Set focus to the input field
	        //userInputField.requestFocus();

	        // Add action listener to the Send button
	        sendButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	sendMessage();
	            }
	        });
	     // Add key listener to the user input field
	        userInputField.addKeyListener(new KeyListener() {
	            @Override
	            public void keyTyped(KeyEvent e) {}

	            @Override
	            public void keyPressed(KeyEvent e) {
	                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                    sendMessage();
	                }
	            }

	            @Override
	            public void keyReleased(KeyEvent e) {}
	        });

	        // Set up layout
	        setLayout(new BorderLayout());
	        // Create a panel for the NORTH region with BoxLayout
	        JPanel northPanel = new JPanel();
	        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
	        
	        // Add title label
	        JLabel titleLabel = new JLabel("<html><div style='text-align: center;'>ChatBot</div></html>");
	        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
	        titleLabel.setHorizontalAlignment(JLabel.CENTER);
	        //titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        northPanel.add(titleLabel);
	        
	        // Add subtitle label
	        JLabel subtitleLabel = new JLabel("<html><div style='text-align: center;'>Connect To The Compiler!</div></html>");
	        subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 14));
	        subtitleLabel.setHorizontalAlignment(JLabel.CENTER);
	        //titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        northPanel.add(subtitleLabel);
	        // Add the panel to the NORTH region
	        add(northPanel, BorderLayout.NORTH);

	        add(new JScrollPane(conversationArea), BorderLayout.CENTER);
	        
	     // Create button panel with "<" button
	        JPanel buttonPanel = new JPanel(new GridLayout(9,1));
	        JButton toggleButton = createToggleButton();
	        buttonPanel.add(toggleButton);
	        //buttons
	        JButton arithButton = new JButton("Arithmetic expressions");
	        JButton stringButton = new JButton("String expression");
	        buttonPanel.add(arithButton);
	        buttonPanel.add(stringButton);
	        Dimension buttonSize = new Dimension(100, 60);
	        arithButton.setPreferredSize(buttonSize);
	        stringButton.setPreferredSize(buttonSize);
	        //action listeners to both arith and strings buttons
	        arithButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	arith=true;
	            	strings=false;
	            	conversationArea.append("Enter a mathematical expression: \n");
	            	//System.out.println("String button pressed. arith=" + arith + ", strings=" + strings);
	            }
	        });
	        stringButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	strings=true;
	            	arith=false;
	            	conversationArea.append("Enter a string of characters: \n");
	            	//System.out.println("String button pressed. arith=" + arith + ", strings=" + strings);
	            }
	        });
	        
	        // Create info panel (initially hidden)
	        infoPanel = createInfoPanel();
	        infoPanel.setVisible(false); 
	        
	        //setting input panel
	        JPanel inputPanel = new JPanel();
	        inputPanel.setLayout(new BorderLayout());
	        inputPanel.add(userInputField, BorderLayout.CENTER);
	        inputPanel.add(sendButton, BorderLayout.EAST);
	        
	     // Create a container panel for the button and info panels
	        JPanel westPanel = new JPanel(new BorderLayout());
	        westPanel.add(buttonPanel, BorderLayout.WEST);
	        westPanel.add(infoPanel, BorderLayout.CENTER);

	        // Add the container panel to the WEST region
	        add(westPanel, BorderLayout.WEST);
	        add(inputPanel, BorderLayout.SOUTH);
	        
	     
	        
	}
	private JButton createToggleButton() {
        //ImageIcon icon = new ImageIcon("./left_arrow.png");
        JButton toggleButton = new JButton("infos :");
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleInfoPanel();
            }
        });
        return toggleButton;
    }
	private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        //panel.setBackground(Color.LIGHT_GRAY);

        JLabel infoLabel = new JLabel("Informations:");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 13));
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(infoLabel, BorderLayout.NORTH);
        //infoLabel.getPreferredSize();
        // Set the preferred size of the label
        //infoLabel.setPreferredSize(infoLabel.getPreferredSize());

        JTextArea infoTextArea = new JTextArea("choose your prefered EBNF tester:\n We created two diffrernt testers the first is for arithmetic expressions and the other is for string expressions.");
        
        infoTextArea.setEditable(false);
        infoTextArea.setLineWrap(true);
        infoTextArea.setOpaque(false); // Make the label non-opaque;
        infoTextArea.setBorder(BorderFactory.createEmptyBorder());
        panel.add(new JScrollPane(infoTextArea), BorderLayout.CENTER);

        return panel;
    }
	private void adjustInfoPanelSize() {
	    // Set the preferred size of the infoPanel to be 30% of the window width
	    int windowWidth = getWidth();
	    int preferredWidth = (int) (windowWidth * 0.3);
	    infoPanel.setPreferredSize(new Dimension(preferredWidth, infoPanel.getPreferredSize().height));

	    // Revalidate and repaint the container
	    revalidate();
	    repaint();
	}
	private void toggleInfoPanel() {
        infoPanel.setVisible(!infoPanel.isVisible());
        adjustInfoPanelSize();
        revalidate();
        repaint();
    }
	//displaying the message
	private void displayMessage(String sender, String message) {
		conversationArea.append(sender + ": " + message + "\n");
	}
	//connecting to Botresponce.java
	private void sendMessage() {
        String userMessage = userInputField.getText();
        displayMessage("You", userMessage);

        // Process the user's message using ChatBotLogic (jumping from Botresponse to arithm or String_ver)
        String botResponse;
        if (arith &&! strings) {
            // Access file for arithmetic processing
            botResponse = arithm.getBotResponse(userMessage);
        } else if (strings && !arith) {
            // Access file for string processing
            botResponse = String_ver.getBotResponse(userMessage);
        } else {
            // Default processing if neither button is pressed
            botResponse = Botresponse.getBotResponse(userMessage);
        }

        displayMessage("Bot", botResponse);

        // Clear the user input field
        userInputField.setText("");
    }


	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new ChatbotGUI().setVisible(true);
	            }
	     });
    }
}

