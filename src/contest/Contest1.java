package contest;

import java.util.HashMap;

public class Contest1 {
    /**
     * 6070.计算字符串的数字和
     *
     * @param s 字符串
     * @param k 长度k
     * @return 新的字符串
     */
    public String digitSum(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() > k) {
            StringBuilder temp = new StringBuilder();
            int sum = 0;
            for (int i = 0; i < sb.length(); i++) {
                int a = sb.charAt(i) - '0';
                sum += a;
                if ((i + 1) % k == 0 || i == sb.length() - 1) {
                    temp.append(sum);
                    sum = 0;
                }
            }
            sb = temp;
        }
        return sb.toString();
    }

    /**
     * 6071.完成所有任务需要的最少轮数
     * 每一轮中,可以完成2、3嘅相同难度级别的任务
     *
     * @param tasks 整数素组, task[i]代表任务难度级别
     * @return 完成所有任务最少轮数
     */
    public int minimumRounds(int[] tasks) {
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.get(key) == 1) {
                return -1;
            }
            int rest = 0;
            if (map.get(key) % 3 != 0) rest = 1;
            ans += map.get(key) / 3 + rest;
        }
        return ans;
    }


    /**
     * TODO
     * 6072.转角路径的成绩中最多能够有几个尾随0
     *
     * @param grid 二维数组
     * @return 最多尾随0的个数
     */
    public int maxTrailingZeros(int[][] grid) {
        int ans = 0;

        for (int i = 0; i < grid.length; i++) {
            ans = Math.max(backtrack(grid, 1, false, 0, i, 0, false), ans);
            ans = Math.max(ans, backtrack(grid, 1, false, 0, i, grid[i].length - 1, false));
        }
        for (int j = 0; j < grid[0].length; j++) {
            ans = Math.max(backtrack(grid, 1, false, 0, 0, j, true), ans);
            ans = Math.max(ans, backtrack(grid, 1, false, 0, grid[0].length - 1, j, true));
        }
        return ans;
    }

    public int backtrack(int[][] grid, int cur, boolean turn, int max, int i, int j, boolean vertical) {
        int temp = cur;
        int count = 0;
        while (temp % 10 == 0) {
            count++;
            temp /= 10;
        }
        max = Math.max(count, max);

        if (i >= grid.length || i < 0 || j >= grid[i].length || j < 0) {
            return max;
        }
        if (!turn) {
            if (vertical) {
                backtrack(grid, cur * grid[i][j], true, max, i, j + 1, false);
            } else {
                backtrack(grid, cur * grid[i][j], true, max, i + 1, j, true);
            }
        }
        if (vertical) {
            backtrack(grid, cur * grid[i][j], false, max, i + 1, j, true);
        } else {
            backtrack(grid, cur * grid[i][j], false, max, i, j + 1, false);
        }

        return max;
    }


    public static void main(String[] args) {
        Contest1 c = new Contest1();
//        //1
//        System.out.println(c.digitSum("111222456", 3));
//        System.out.println(c.digitSum("0000000", 3));
//        System.out.println(c.digitSum("11111222223", 3));
//        System.out.println(c.digitSum("11111222223", 6));
//        //2
//        System.out.println(c.minimumRounds(new int[]{2, 2, 3, 3, 2, 4, 4, 4, 4, 4}));

        System.out.println(c.maxTrailingZeros(new int[][]{{23, 17, 15, 3, 20}, {8, 1, 20, 27, 11}, {9, 4, 6, 2, 21}, {40, 9, 1, 10, 6}, {22, 7, 4, 5, 3}}));
    }
}
