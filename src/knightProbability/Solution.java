package knightProbability;

public class Solution {
    /**
     *
     在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
     象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
     * @tag 动态规划  dynamic programming
     solution1 递归(超时)
     */
    public double knightProbability(int n, int k, int row, int column) {
        if (row >= n || column >= n || row < 0 || column < 0) {
            return 0;
        }
        if (k == 0) {
            return 1;
        } else {
            double result = 0;
            k--;
            result += 0.125 * knightProbability(n, k, row + 2, column + 1)
                    + 0.125 * knightProbability(n, k, row + 2, column - 1)
                    + 0.125 * knightProbability(n, k, row + 1, column + 2)
                    + 0.125 * knightProbability(n, k, row + 1, column - 2)
                    + 0.125 * knightProbability(n, k, row - 2, column - 1)
                    + 0.125 * knightProbability(n, k, row - 2, column + 1)
                    + 0.125 * knightProbability(n, k, row - 1, column + 2)
                    + 0.125 * knightProbability(n, k, row - 1, column - 2);
            return result;
        }

    }

}
