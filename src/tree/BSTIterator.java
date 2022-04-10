package tree;

import DataStructures.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BSTIterator {

    List<Integer> list;
    Iterator<Integer> iterator;
    public BSTIterator(TreeNode root) {
        list = new ArrayList<>();
        inorder(root, list);
        iterator = list.iterator();
    }

    public int next() {
        return iterator.next();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public void inorder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }
}
