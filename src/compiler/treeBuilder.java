package compiler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class treeBuilder {

    private static BufferedReader reader;

    public static void main(String[] args) {
        try {
            String filePath = "C:\\Users\\thinkpad\\Desktop\\INSEA\\studies\\2A\\info theo-compiler\\compiler\\Projet_Technique_Compilation\\arithm.txt";  // Replace with the path to your token file
            reader = new BufferedReader(new FileReader(filePath));

            TreeNode root = buildTree();
            printTree(root, 0);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static TreeNode buildTree() throws IOException {
        String line = reader.readLine();
        if (line != null) {
            String[] tokens = line.split("\\s+");
            return buildTree(tokens);
        }
        return null;
    }

    private static TreeNode buildTree(String[] tokens) {
        if (tokens.length == 0) {
            return null;
        }

        TreeNode node = new TreeNode(tokens[0]);
        List<String> remainingTokens = new ArrayList<>();
        for (int i = 1; i < tokens.length; i++) {
            remainingTokens.add(tokens[i]);
        }

        while (!remainingTokens.isEmpty()) {
            TreeNode child = buildTree(remainingTokens.toArray(new String[0]));
            if (child != null) {
                node.children.add(child);
            }
        }

        return node;
    }

    private static void printTree(TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println(node.value);

        for (TreeNode child : node.children) {
            printTree(child, depth + 1);
        }
    }
}
