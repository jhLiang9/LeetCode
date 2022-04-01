import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    /**
     * 2055. 蜡烛之间的盘子
     * 给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0开始的字符串s，它只包含字符'*' 和'|'，其中'*'表示一个 盘子，'|'表示一支蜡烛。
     * <p>
     * 同时给你一个下标从 0开始的二维整数数组queries，其中queries[i] = [lefti, righti]表示 子字符串s[lefti...righti]（包含左右端点的字符）。对于每个查询，你需要找到 子字符串中在 两支蜡烛之间的盘子的 数目。如果一个盘子在 子字符串中左边和右边 都至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间。
     * <p>
     * <p>
     * 解法: 前缀和
     *
     * @param s       字符串s
     * @param queries 查询条件
     * @return 请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
     */
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] preSum = new int[n];
        for (int i = 0, sum = 0; i < n; i++) {
            if (s.charAt(i) == '*') {
                sum++;
            }
            preSum[i] = sum;
        }
        int[] left = new int[n];
        for (int i = 0, l = -1; i < n; i++) {
            if (s.charAt(i) == '|') {
                l = i;
            }
            left[i] = l;
        }
        int[] right = new int[n];
        for (int i = n - 1, r = -1; i >= 0; i--) {
            if (s.charAt(i) == '|') {
                r = i;
            }
            right[i] = r;
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int x = right[query[0]], y = left[query[1]];
            ans[i] = x == -1 || y == -1 || x >= y ? 0 : preSum[y] - preSum[x];
        }
        return ans;
    }


    //暴力 超时
//    public int[] platesBetweenCandles(String s, int[][] queries) {
//        int[] result = new int[queries.length];
//        for (int i = 0; i < queries.length; i++) {
//            int start = queries[i][0];
//            int end = queries[i][1];
//            while (start < end) {
//                if (s.charAt(start) != '|') {
//                    start++;
//                } else if ('|' != s.charAt(end)) {
//                    end--;
//                } else {
//                    int count = 0;
//                    for (int j = start; j < end; j++) {
//                        if (s.charAt(j) == '*') {
//                            count++;
//                        }
//                    }
//                    result[i] = count;
//                    break;
//                }
//            }
//        }
//        return result;
//    }


    /**
     * 798. 得分最高的最小轮调
     * 给你一个数组nums，我们可以将它按一个非负整数 k 进行轮调，这样可以使数组变为[nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]]的形式。此后，任何值小于或等于其索引的项都可以记作一分。
     * <p>
     * 例如，数组为nums = [2,4,1,3,0]，我们按k = 2进行轮调后，它将变成[1,3,0,2,4]。这将记为 3 分，因为 1 > 0 [不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
     * 在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。如果有多个答案，返回满足条件的最小的下标 k 。
     *
     * @param nums 给定数组 nums
     * @return 下标k
     */


    public int bestRotation(int[] nums) {
        int n = nums.length;
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            int low = (i + 1) % n;
            int high = (i - nums[i] + n + 1) % n;
            diffs[low]++;
            diffs[high]--;
            if (low >= high) {
                diffs[0]++;
            }
        }
        int bestIndex = 0;
        int maxScore = 0;
        int score = 0;
        for (int i = 0; i < n; i++) {
            score += diffs[i];
            if (score > maxScore) {
                bestIndex = i;
                maxScore = score;
            }
        }
        return bestIndex;
    }


    /**
     * 599. 两个列表的最小索引总和
     *
     * @param list1 列表1
     * @param list2 列表2
     * @return 最小索引和
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, Integer> result = new HashMap<>();
        List<String> s = new ArrayList<>();

        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                result.put(list2[i], map.get(list2[i]) + i);
            }
        }
        int min = Integer.MAX_VALUE;
        for (String key : result.keySet()) {
            int cur = result.get(key);
            if (cur < min) {
                min = cur;
            }
        }
        for (String key : result.keySet()) {
            int cur = result.get(key);
            if (cur == min) {
                s.add(key);
            }
        }
        return s.toArray(new String[0]);
    }

    /**
     * 240. 搜索二维矩阵 II
     *
     * @param matrix 矩阵
     * @param target target元素
     * @return 是否包含target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        return find(matrix, 0, matrix[0].length - 1, target);
    }

    public boolean find(int[][] matrix, int i, int j, int target) {
        if (i >= matrix.length || i < 0 || j < 0 || j > matrix[0].length) return false;
        if (matrix[i][j] == target) return true;
        if (matrix[i][j] < target) {
            return find(matrix, i + 1, j, target);
        } else {
            return find(matrix, i, j - 1, target);
        }
    }


    public static void main(String[] args) {

    }
}
