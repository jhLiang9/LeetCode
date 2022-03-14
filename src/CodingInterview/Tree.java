package CodingInterview;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    /**
     给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
     * @param root 树的根节点
     * @return 后序遍历
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    public void dfs(TreeNode node, List<Integer> result) {
        if (node == null) return;
        dfs(node.left, result);
        dfs(node.right, result);
        result.add(node.val);
    }

}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}