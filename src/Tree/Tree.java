package Tree;

import DataStructures.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree {

    /**
     * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
     *
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

    /**
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     *
     * @param root 根节点
     * @return 翻转后的根节点
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        dfsInvert(root);
        return root;
    }

    public void dfsInvert(TreeNode node) {
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        if (node.left != null) {
            dfsInvert(node.left);
        }
        if (node.right != null) {
            dfsInvert(node.right);
        }
    }


    /**
     * 114. 二叉树展开为链表
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * <p>
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     * <p>
     * 输入：root = [1,2,5,3,4,null,6]
     * 输出：[1,null,2,null,3,null,4,null,5,null,6]
     *
     * @param root 二叉树的根结点
     */
    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode left = cur.left;
                TreeNode end = left;
                while (end.right != null) {
                    end = end.right;
                }
                end.right = cur.right;
                cur.left = null;
                cur.right = left;
            }
            cur = cur.right;
        }
    }

    /**
     * 103. 二叉树的锯齿形层序遍历
     * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     *
     * @param root 二叉树的根节点 root
     * @return 节点值的 锯齿形层序遍历
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return new ArrayList<>();
        stack.push(root);
        boolean flag = true;

        while (!stack.isEmpty()) {
            //使用一个临时栈保存,该层结点pop出的左右结点
            Deque<TreeNode> tempStack = new ArrayDeque<>();
            List<Integer> temp = new ArrayList<>();
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                temp.add(node.val);
                // 通过flag做判断, 即分辨从左到右还是从右到左
                if (flag) {
                    if (node.left != null) {
                        tempStack.push(node.left);
                    }
                    if (node.right != null) {
                        tempStack.push(node.right);
                    }
                } else {
                    if (node.right != null) {
                        tempStack.push(node.right);
                    }
                    if (node.left != null) {
                        tempStack.push(node.left);
                    }
                }

            }
            flag = !flag;
            result.add(temp);
            stack = tempStack;
        }

        return result;

    }


    /**
     * 剑指 Offer II 052. 展平二叉搜索树
     * https://leetcode-cn.com/problems/NYBBNL/
     * <p>
     * 给你一棵二叉搜索树，请 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，
     * 并且每个节点没有左子节点，只有一个右子节点。
     *
     * @param root 根节点
     * @return 根节点
     */
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) return null;
        TreeNode head = new TreeNode(-1);
        List<TreeNode> list = new ArrayList<>();
        inOrder(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).right = list.get(i + 1);
        }
        return list.get(0);
    }

    public void inOrder(TreeNode node, List<TreeNode> list) {
        if (node == null) return;
        inOrder(node.left, list);
        list.add(node);
        inOrder(node.right, list);
        node.right = null;
        node.left = null;
    }

    //671
    int ans;
    int rootValue;

    /**
     * 671. 二叉树中第二小的节点
     *
     * @param root 根节点
     * @return 二叉树中第二小的节点
     */
    public int findSecondMinimumValue(TreeNode root) {
        ans = -1;
        rootValue = root.val;
        dfsFindSecondMinimumValue(root);
        return ans;
    }

    public void dfsFindSecondMinimumValue(TreeNode node) {
        if (node == null) {
            return;
        }
        if (ans != -1 && node.val >= ans) {
            return;
        }
        if (node.val > rootValue) {
            ans = node.val;
        }
        dfsFindSecondMinimumValue(node.left);
        dfsFindSecondMinimumValue(node.right);
    }

    /**
     * 606. 根据二叉树创建字符串
     */

    public String tree2str(TreeNode root) {
        String result = recurTree2str(root);
        return result.substring(1, result.length() - 1);
    }

    public String recurTree2str(TreeNode node) {
        if (node.left == null && node.right == null) {
            return "(" + node.val + ")";
        }
        String res = "(" + node.val;
        if (node.left != null) {
            res += recurTree2str(node.left);
        } else {
            res += "()";
        }
        if (node.right != null) {
            res += recurTree2str(node.right) + ")";
        } else {
            res += ")";
        }
        return res;
    }

    /**
     * 653. 两数之和 IV - 输入 BST
     * EASY
     *
     * @param root 根节点
     * @param k    目标
     * @return 是否包含该值
     */
    public boolean findTarget(TreeNode root, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        inorderFindTarget(root, list);
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (list.get(left) + list.get(right) > k) {
                right--;
            } else if (list.get(left) + list.get(right) == k) {
                return true;
            } else {
                left++;
            }
        }
        return false;
    }

    public void inorderFindTarget(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inorderFindTarget(node.left, list);
        list.add(node.val);
        inorderFindTarget(node.right, list);
    }

    /**
     * 440. 字典序的第K小数字
     * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
     * 思路: 字典树
     *
     * @param n 整数n
     * @param k 整数k
     * @return [1, n] 中字典序第 k 小的数字
     */

    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--;
        while (k > 0) {
            //curr当前节点下子节点数量
            int steps = getSteps(curr, n);
            //如果子节点数量<=k，那k直接减去，说明k并不在这个区间内
            if (steps <= k) {
                k -= steps;
                curr++;
            } else {
                //节点数量比k多,一层一层深入计算节点数量;
                curr = curr * 10;
                k--;
            }
        }
        return curr;
    }

    //找到当前 节点 下的子节点数量
    public int getSteps(int curr, long n) {
        int step = 0;
        int left = curr, right = curr;
        while (left <= n) {
            step += Math.min(right, n) - left + 1;
            left = left * 10;
            right = right * 10 + 9;
        }
        return step;
    }


}

