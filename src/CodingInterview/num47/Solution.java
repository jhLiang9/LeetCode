package CodingInterview.num47;

/**
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 *
 * @tag 动态规划  dynamic programming
 */
class Solution {
    public int maxValue(int[][] grid) {

        int n = grid[0].length;
        int m = grid.length;

        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i - 1 >= 0 && j - 1 >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j]);
                } else if (i - 1 >= 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else if (j - 1 >= 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                }

            }
        }
        return dp[m - 1][n - 1];
    }
}