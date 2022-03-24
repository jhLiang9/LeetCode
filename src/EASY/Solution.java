package EASY;

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

}
