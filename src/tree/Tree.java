package tree;

import dataStructures.ListNode;
import dataStructures.Node;
import dataStructures.TreeNode;

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
            if (res != null) {
                if (node.left != null) {
                    queue.offer(node.left);
                    queue1.offer(res.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    queue1.offer(res.right);
                }
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


    /**
     * 112. 路径总和
     *
     * @param root      根节点
     * @param targetSum 目标和
     * @return 是否有目标和
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == root.right) return root.val == targetSum; //都为null
        return order112(root.left, root.val, targetSum) || order112(root.right, root.val, targetSum);
    }

    private boolean order112(TreeNode node, int pre, int targetSum) {
        if (node == null) return false;
        if (node.left == null && node.right == null) return (pre + node.val) == targetSum;
        if (node.left != null && node.right != null)
            return order112(node.left, pre + node.val, targetSum) || order112(node.right, pre + node.val, targetSum);
        if (node.left != null) return order112(node.left, pre + node.val, targetSum);
        return order112(node.right, pre + node.val, targetSum);
    }

    /**
     * 429. N 叉树的层序遍历
     *
     * @param root 根节点
     * @return 层序遍历
     */
    public List<List<Integer>> levelOrder(Node root) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if (root == null) return ans;
        LinkedList<Node> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            LinkedList<Integer> temp = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                Node node = deque.poll();
                temp.add(node.val);
                if (node.children.isEmpty()) continue;
                for (Node child : node.children) {
                    deque.offer(child);
                }
            }
            ans.add(temp);
        }
        return ans;
    }

    /**
     * 637. 二叉树的层平均值
     *
     * @param root 根节点
     * @return 层平均值
     */
    public List<Double> averageOfLevels(TreeNode root) {
        LinkedList<Double> ans = new LinkedList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        if (root == null) return ans;
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.poll();
                sum += node.val;
                if (node.left != null) deque.offer(node.left);
                if (node.right != null) deque.offer(node.right);
            }
            ans.add(sum / size);
        }
        return ans;
    }

    /**
     * 96. 不同的二叉搜索树
     *
     * @param n 节点个数
     * @return 不同二叉树的棵树
     */
    public int numTrees(int n) {
        int ans = 0;
        ArrayList<TreeNode> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            //i作为头
            TreeNode root = new TreeNode(i + 1);
            boolean[] visited = new boolean[n];
            visited[i] = true;
            treesCount(root, root, n, 1, list, visited);
        }
        for (TreeNode node : list) {
            System.out.println(node.val);
        }
        return ans;
    }

    public void treesCount(TreeNode root, TreeNode node, int n, int cur, List<TreeNode> list, boolean[] visited) {
        if (cur == n) {
            list.add(root);
            return;
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                //没访问过的
                if (i + 1 < node.val) {
                    //左节点
                    visited[i] = true;
                    node.left = new TreeNode(i + 1);
                    treesCount(root, node.left, n, cur + 1, list, visited);
                    visited[i] = false;
                }
                if (i + 1 > node.val) {
                    //右节点
                    visited[i] = true;
                    node.right = new TreeNode(i + 1);
                    treesCount(root, node.right, n, cur + 1, list, visited);
                    visited[i] = false;
                }
            }
        }

    }

    /**
     * 386. 字典序排数
     *
     * @param n 按字典序返回范围 [1, n] 内所有整数
     * @return 按字典序返回范围 [1, n] 内所有整数。
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        int num = 1;
        for (int i = 0; i < n; i++) {
            ans.add(num);
            if (num * 10 < n) {
                num *= 10;
            } else {
                while (num % 10 == 9 || num + 1 > n) {
                    num /= 10;
                }
                num++;
            }
        }
        return ans;
    }

    /**
     * 1305. 两棵二叉搜索树中的所有元素
     *
     * @param root1 二叉树1
     * @param root2 二叉树1
     * @return 排序后的所有元素
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> t1 = new ArrayList<>();
        List<Integer> t2 = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        order(root1, t1);
        order(root2, t2);
        int i = 0, j = 0;
        while (i != t1.size() && j != t2.size()) {
            if (t1.get(i) <= t2.get(j)) {
                res.add(t1.get(i++));
            } else {
                res.add(t2.get(j++));
            }
        }

        if (i == t1.size()) {
            while (j != t2.size()) {
                res.add(t2.get(j++));
            }
        }

        while (i != t1.size()) {
            res.add(t1.get(i++));
        }
        return res;
    }

    public void order(TreeNode root, List<Integer> list) {
        if (root == null) return;
        order(root.left, list);
        list.add(root.val);
        order(root.right, list);
    }

    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int res = 0;
        int max = Integer.MIN_VALUE;
        int level = 0;
        while (true) {
            Deque<TreeNode> tempQueue = new ArrayDeque<>();
            int count = 0;
            level++;
            while (!queue.isEmpty()) {
                TreeNode node = queue.pollLast();
                if (node != null) {
                    count += node.val;
                    if (node.left != null) tempQueue.offer(node.left);
                    if (node.right != null) tempQueue.offer(node.right);
                }
            }
            if (count > max) {
                max = count;
                res = level;
            }
            queue.addAll(tempQueue);
            if (queue.isEmpty()) break;
        }
        return res;
    }

    /**
     * 199. 二叉树的右视图
     * desc: 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     * 思路: 核心是层序遍历，每层用List记录， 到达层的最后一位时，保存。
     *
     * @param root 根节点
     * @return 右侧看到的结点值
     */
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            List<TreeNode> list = new ArrayList<>();
            int count = deque.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = deque.pollFirst();
                if (node.left != null) list.add(node.left);
                if (node.right != null) list.add(node.right);
                if (deque.isEmpty()) res.add(node.val);
            }
            deque.addAll(list);
        }
        return res;
    }

    /**
     * 129. 求根节点到叶节点数字之和
     * desc : 计算从根节点到叶节点生成的 所有数字之和 。
     *
     * @param root 根节点
     * @return 宿友数字之和
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        return rec(root, 0);
    }

    /**
     * 129 用的函数
     *
     * @param node    node 树节点
     * @param prevSum 之前的总和
     * @return 当前节点的总和
     */
    public int rec(TreeNode node, int prevSum) {
        if (node == null) return 0;
        prevSum = prevSum * 10 + node.val;
        if (node.left == null && node.right == null) return prevSum;
        return rec(node.left, prevSum) + rec(node.right, prevSum);
    }

    /**
     * 654. 最大二叉树
     * desc: https://leetcode.cn/problems/maximum-binary-tree/
     *
     * @param nums 给定一个不重复的整数数组 nums
     * @return 最大二叉树
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int max = -1;
        int maxIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        TreeNode node = new TreeNode(max);
        int[] leftNums = new int[maxIndex];
        int[] rightNums = new int[nums.length - maxIndex - 1];
        System.arraycopy(nums, 0, leftNums, 0, leftNums.length);
        System.arraycopy(nums, maxIndex + 1, rightNums, 0, rightNums.length);
        //递归 求得当前节点的左节点和右节点
        node.left = constructMaximumBinaryTree(leftNums);
        node.right = constructMaximumBinaryTree(rightNums);
        return node;
    }

    /**
     * 113. 路径总和 II
     * @param root 根节点
     * @param targetSum 目标数
     * @return 根节点到目标的路径
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        fun(res, new LinkedList<>(), root, targetSum);
        return res;
    }
    //113
    public void fun(List<List<Integer>> res, LinkedList<Integer> deque,TreeNode node, int currentSum) {
        if(node == null) return;
        // 回溯 递归
        deque.offer(node.val);
        if(node.left == null && node.right == null && currentSum - node.val ==0) res.add(new LinkedList<>(deque));
        fun(res, deque, node.left, currentSum - node.val);
        fun(res, deque, node.right, currentSum - node.val);
        deque.pollLast();
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2};

        Tree tree = new Tree();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.right.right = new TreeNode(11);

    }
}

