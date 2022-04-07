package Tree;

import DataStructures.ListNode;
import DataStructures.TreeNode;

import java.util.*;

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


    /**
     * 110. 平衡二叉树
     *
     * @param root 根节点
     * @return 是否平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }


    /**
     * 310. 最小高度树
     *
     * @param n
     * @param edges 边
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int res = bfs(new boolean[n], i, edges, 1);
            if (res == min) {
                ans.add(i);
            } else if (res < min) {
                min = res;
                ans.clear();
                ans.add(i);
            }
        }
        return ans;
    }

    private int bfs(boolean[] visited, int node, int[][] edges, int depth) {
        if (visited[node]) return depth;
        visited[node] = true;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int[] edge : edges) {
            if (edge[0] == node && !visited[edge[1]]) {
                queue.offer(edge[1]);
//                if (!visited[edge[1]]) return bfs(visited, edge[1], edges, depth + 1);
            } else if (edge[1] == node && !visited[edge[0]]) {
                queue.offer(edge[0]);
//                if (!visited[edge[0]]) return bfs(visited, edge[0], edges, depth + 1);
            }
        }
        int res = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            res = Math.min(res, bfs(visited, queue.poll(), edges, depth + 1));
        }
        return res;
    }

    /**
     * 1379. 找出克隆二叉树中的相同节点
     *
     * @param original 原始树
     * @param cloned   克隆树
     * @param target   目标节点
     * @return 克隆树对应的目标节点
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        TreeNode res = null;
        if (original == target) return cloned;
        Queue<TreeNode> queue = new ArrayDeque<>();
        Queue<TreeNode> queue1 = new ArrayDeque<>();
        queue.offer(original);
        queue1.offer(cloned);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res = queue1.poll();
            if (node == target) return res;
            if (node.left != null) {
                queue.offer(node.left);
                queue1.offer(res.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
                queue1.offer(res.right);
            }
        }
        return res;
    }

    /**
     * 1302. 层数最深叶子节点的和
     *
     * @param root 根节点
     * @return 最深叶子节点的和
     */
    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        int ans = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            //全部排干净
            List<TreeNode> list = new ArrayList<>();
            ans = 0;
            while (!queue.isEmpty()) {
                list.add(queue.poll());
            }
            for (TreeNode node : list) {
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                ans += node.val;
            }
        }
        return ans;
    }

    /**
     * 面试题 04.03. 特定深度节点链表
     *
     * @param tree 根节点
     * @return 节点链表
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<ListNode> ans = new ArrayList<>();
        queue.offer(tree);
        while (!queue.isEmpty()) {
            ListNode node = new ListNode(-1);
            ListNode start = node;
            List<TreeNode> list = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode poll = queue.poll();
                list.add(poll);
                node.next = new ListNode(poll.val);
                node = node.next;
            }
            ans.add(start.next);

            for (TreeNode treeNode : list) {
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
        }
        ListNode[] res = new ListNode[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }

        return res;
    }

    /**
     * 111. 二叉树的最小深度
     *
     * @param root 根节点
     * @return 最小深度
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (root.right != null && root.left == null) return 1 + minDepth(root.right);
        if (root.right == null) return 1 + minDepth(root.left);
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    /**
     * 107. 二叉树的层序遍历 II
     *
     * @param root 根节点
     * @return 字底向上的层序遍历
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if (root == null) return ans;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int count = deque.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = deque.poll();
                if (node == null) continue;
                list.add(node.val);
                if (node.left != null) deque.offer(node.left);
                if (node.right != null) deque.offer(node.right);
            }
            ans.push(list);
        }
        return ans;
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.findMinHeightTrees(6, new int[][]{{3, 0}, {3, 1}, {3, 2}, {5, 4}, {3, 4}});
    }
}

