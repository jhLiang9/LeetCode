package EASY;

import DataStructures.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    /**
     * 661. 图片平滑器
     */
    public int[][] imageSmoother(int[][] img) {
        int m = img.length, n = img[0].length;
        int[][] ret = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = 0, sum = 0;
                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
                        if (x >= 0 && x < m && y >= 0 && y < n) {
                            num++;
                            sum += img[x][y];
                        }
                    }
                }
                ret[i][j] = sum / num;
            }
        }
        return ret;
    }

    /**
     * 172. 阶乘后的零
     *
     * @param n 整数 n
     * @return 尾随0 的数量
     * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
     */
    public int trailingZeroes(int n) {
        //找2和5的数量
        if (n == 0) return 0;
        int start = 1;
        int count = 0;
        int count2 = 0;
        int count5 = 0;

        while (start++ != n) {
            int temp = start;
            while (temp != 0) {
                if (temp % 10 == 0) {
                    temp /= 10;
                    count++;
                } else if (temp % 5 == 0) {
                    temp /= 5;
                    count5++;
                } else if (temp % 2 == 0) {
                    temp /= 2;
                    count2++;
                } else {
                    break;
                }
            }
        }
        return count + Math.min(count2, count5);
    }

    //Solution 2

    public int trailingZeroes2(int n) {
        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }

    /**
     * 682. 棒球比赛
     *
     * @param ops 操作
     * @return 最终得分
     */
    public int calPoints(String[] ops) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String op : ops) {
            char c = op.charAt(op.length() - 1);
            if (Character.isDigit(c)) {
                stack.push(Integer.parseInt(op));
            } else {
                switch (op) {
                    case "C":
                        stack.pop();
                        break;
                    case "D":
                        stack.push(stack.peek() * 2);
                        break;
                    case "+":
                        int top = stack.pop();
                        int pushIn = top + stack.peek();
                        stack.push(top);
                        stack.push(pushIn);
                        break;
                }
            }
        }
        int res = 0;
        for (int num : stack) {
            res += num;
        }
        return res;
    }

    /**
     * LCP 44. 开幕式焰火
     *
     * @param root 根节点
     * @return 树中val的数值个数
     */
    public int numColor(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        inorder(set, root);
        return set.size();
    }


    public void inorder(Set<Integer> set, TreeNode node) {
        if (node.left != null) {
            inorder(set, node.left);
        }
        if (node.right != null) {
            inorder(set, node.right);
        }
        set.add(node.val);
    }

    /**
     * 693. 交替位二进制数
     * @param n 一个正整数
     * @return 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
     *
     */
    public boolean hasAlternatingBits(int n) {
        return (n >> 1 & n) == 0 && (n | n >> 2) == n;
    }


}
