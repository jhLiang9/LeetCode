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

    /**
     * 821. 字符的最短距离
     *
     * @param s 字符串
     * @param c 目标字符
     * @return 最短距离
     * 思路:使用两次遍历
     */
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];

        for (int i = 0, idx = -n; i < n; ++i) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            ans[i] = i - idx;
        }

        for (int i = n - 1, idx = 2 * n; i >= 0; --i) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            ans[i] = Math.min(ans[i], idx - i);
        }
        return ans;
    }

    /**
     * 868. 二进制间距
     *
     * @param n 正整数n
     * @return 二进制最长间距
     */
    public int binaryGap(int n) {
        int ans = 0;
        int prev = -1;
        for (int i = 0; n != 0; ++i) {
            if ((n & 1) == 1) {
                if (prev != -1) {
                    ans = Math.max(ans, i - prev);
                }
                prev = i;
            }
            n >>= 1;
        }
        return ans;
    }


    /**
     * 883. 三维形体投影面积
     *
     * @param grid 矩阵
     * @return 投影面积
     */
    public int projectionArea(int[][] grid) {
        int n = grid.length;
        int res = 0;
        int[] maxX = new int[n];
        int[] maxY = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) res++;
                maxX[i] = Math.max(grid[i][j], maxX[i]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxY[i] = Math.max(grid[j][i], maxY[i]);
            }
        }

        res += Arrays.stream(maxX).sum();
        res += Arrays.stream(maxY).sum();

        return res;
    }

    /**
     * 908. 最小差值 I
     *
     * @param nums 数组
     * @param k    可操作性范围
     * @return
     */
    public int smallestRangeI(int[] nums, int k) {
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }
        return max - min <= 2 * k ? 0 : (max - min) - 2 * k;
    }

    /**
     * 937. 重新排列日志文件
     *
     * @param logs 日志文件
     * @return 排序的日志文件
     */
    public String[] reorderLogFiles(String[] logs) {
        int length = logs.length;
        Pair[] arr = new Pair[length];
        for (int i = 0; i < length; i++) {
            arr[i] = new Pair(logs[i], i);
        }
        Arrays.sort(arr, (a, b) -> logCompare(a, b));
        String[] reordered = new String[length];
        for (int i = 0; i < length; i++) {
            reordered[i] = arr[i].log;
        }
        return reordered;
    }

    public int logCompare(Pair pair1, Pair pair2) {
        String log1 = pair1.log, log2 = pair2.log;
        int index1 = pair1.index, index2 = pair2.index;
        String[] split1 = log1.split(" ", 2);
        String[] split2 = log2.split(" ", 2);
        boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
        boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
        if (isDigit1 && isDigit2) {
            return index1 - index2;
        }
        if (!isDigit1 && !isDigit2) {
            int sc = split1[1].compareTo(split2[1]);
            if (sc != 0) {
                return sc;
            }
            return split1[0].compareTo(split2[0]);
        }
        return isDigit1 ? 1 : -1;
    }

    public static class Pair {
        String log;
        int index;

        public Pair(String log, int index) {
            this.log = log;
            this.index = index;
        }
    }

    /**
     * 942. 增减字符串匹配
     *
     * @param s 字符串s
     * @return 重排
     */
    public int[] diStringMatch(String s) {
        int n = s.length();
        int[] res = new int[n + 1];
        int index = 0;
        int min = 0;
        int max = n;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'D') {
                res[index++] = max;
                max -= 1;
            } else if (s.charAt(i) == 'I') {
                res[index++] = min;
                min += 1;
            }
        }
        res[index] = (min + max) / 2;
        return res;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        s.mostCommonWord("Bob, hit,ball", new String[]{"bob", "hit"});
    }

}

