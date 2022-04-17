package easy;

import DataStructures.TreeNode;

import java.util.*;

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
                        if (stack.peek() == null) break;
                        stack.push(stack.peek() * 2);
                        break;
                    case "+":
                        int top = stack.pop();
                        if (stack.peek() == null) break;
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
     *
     * @param n 一个正整数
     * @return 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
     */
    public boolean hasAlternatingBits(int n) {
        return (n >> 1 & n) == 0 && (n | n >> 2) == n;
    }

    /**
     * 728. 自除数 自除数 是指可以被它包含的每一位数整除的数。
     *
     * @param left  起始
     * @param right 终点
     * @return 自除数个数
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (i % 10 == 0) continue;
            int cur = i;
            boolean flag = true;
            while (cur != 0) {
                int temp = cur % 10;
                if (temp == 0 || i % temp != 0) {
                    flag = false;
                    break;
                }
                cur /= 10;
            }
            if (flag) {
                ans.add(i);
            }
        }
        return ans;
    }


    /**
     * * 762. 二进制表示中质数个计算置位
     *
     * @param left  两个整数 left 和 right
     * @param right 两个整数 left 和 right
     * @return 给你两个整数 left 和 right ，在闭区间 [left, right] 范围内，统计并返回 计算置位位数为质数 的整数个数。
     */
    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            int count = Integer.bitCount(i);
            if (count == 1) {
                continue;
            }
            if (count == 2) {
                ans++;
                continue;
            }
            boolean flag = true;
            for (int j = 2; j * j <= count; j++) {
                if (count % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) ans++;
        }
        return ans;
    }

    /**
     * #66 加一 加1
     *
     * @param digits 原数组
     * @return 结果数组
     */
    public int[] plusOne(int[] digits) {
        int n = digits.length - 1;
        boolean carry = false;
        while (n >= 0) {
            if (digits[n] + 1 == 10) {
                digits[n] = 0;
            } else {
                digits[n] += 1;
                break;
            }
            if (n == 0 && digits[n] == 0) {
                carry = true;
            }
            n--;
        }
        if (carry) {
            int[] ans = new int[digits.length + 1];
            ans[0] = 1;
            System.arraycopy(digits, 0, ans, 1, digits.length);
            return ans;
        }
        return digits;
    }

    /**
     * 1672. 最富有客户的资产总量
     *
     * @param accounts 账户数
     * @return 最大值
     */
    public int maximumWealth(int[][] accounts) {
        int max = -1;
        for (int[] account : accounts) {
            int sum = 0;
            for (int i : account) {
                sum += i;
            }
            if (sum > max) max = sum;
        }
        return max;
    }

    /**
     * 819. 最常见的单词
     *
     * @param paragraph 段落
     * @param banned    禁用
     * @return 最常见的单词
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph = paragraph.toLowerCase();
        String[] split = paragraph.split("[ ,.!?:;']");
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : split) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (String ban : banned) {
            map.remove(ban);
        }
        int max = -1;
        String ans = "";
        for (String key : map.keySet()) {
            if (map.get(key) > max && !key.equals("")) {
                ans = key;
                max = map.get(key);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.mostCommonWord("Bob, hit,ball", new String[]{"bob", "hit"});
    }

}
