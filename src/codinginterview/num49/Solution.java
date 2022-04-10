package codinginterview.num49;

class Solution {
    public int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int d0 = dp[a] * 2;
            int d1 = dp[b] * 3;
            int d2 = dp[c] * 5;
            int min = Math.min(d0, Math.min(d1, d2));
            if (min == d0) a++;
            if (min == d1) b++;
            if (min == d2) c++;
            System.out.println(min);
            dp[i] = min;
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.nthUglyNumber(10);
    }
}
