public class Solution {
    public double knightProbability(int n, int k, int row, int column) {
        if (row >= n || column >= n || row < 0 || column < 0) {
            return 0;
        }

        if (k == 0) {
            return 1;
        } else {
            double result = 0;

            k--;
            result +=
                    0.125 * knightProbability(n, k, row + 2, column + 1)
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

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.knightProbability(3, 2, 0, 0));
    }
}
