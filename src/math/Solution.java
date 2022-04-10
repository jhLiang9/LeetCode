package math;

import java.util.Arrays;

public class Solution {
    /**
     * 204. 计数质数
     * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
     *
     * @param n 给定整数 n
     * @return 小于非负整数 n 的质数的数量
     * <p>
     * Solution:常用方法是使用枚举，对每个数都单独判断是否是质数，时间复杂度为O(n根号n)
     * 枚举没有考虑到数与数的关联性，因此难以再继续优化时间复杂度。接下来我们介绍一个常见的算法，该算法由希腊数学家厄拉多塞（\rm EratosthenesEratosthenes）提出，称为厄拉多塞筛法，简称埃氏筛。
     * 如果 xx 是质数，那么大于 xx 的 xx 的倍数 2x,3x,… 一定不是质数，因此可以从这里入手。 时间复杂度为O(NlogN)
     */
    public int countPrimes(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }


    /**
     * 279. 完全平方数给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
     * <p>
     * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     *
     * @param n 整数 n
     * @return 完全平方数的最少数量
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        //i从1开始, dp[0]的值为0,不动
        //i<=n i为n的时候计算结果
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(dp[i - j * j], min);
            }
            // 前一个状态 +1  dp[25] = dp[0]+1; dp[26] = dp[25]+1;
            dp[i] = min + 1;
        }
        return dp[n];
    }
}
