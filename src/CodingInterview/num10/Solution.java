package CodingInterview.num10;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * @tag 动态规划  dynamic programming
 */
class Solution {
    public int fib(int n) {
        if (n == 0 || n == 1) return n;
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 2] +
                    dp[i - 1]) % 1000000007;
        }

        return (int) dp[n];
    }
}