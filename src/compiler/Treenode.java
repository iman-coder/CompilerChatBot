package compiler;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    String value;
    List<TreeNode> children;

    TreeNode(String value) {
        this.value = value;
        this.children = new ArrayList<>();
    }
}


