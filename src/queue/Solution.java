package queue;

public class Solution {
    /**
     * 713. 乘积小于 K 的子数组
     * 滑动窗口
     * 模拟
     *
     * @param nums 数组
     * @param k    K值
     * @return 子数组数量
     */
    public int numSubarrayProductLessThanK0(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int index = i;
            while (cur < k) {
                res++;
                index++;
                if (index < nums.length)
                    cur *= nums[index];
                else cur = k;

            }
        }
        return res;
    }

    //滑动窗口做法
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length, ret = 0;
        int prod = 1, i = 0;
        for (int j = 0; j < n; j++) {
            prod *= nums[j];
            while (i <= j && prod >= k) {
                prod /= nums[i];
                i++;
            }
            ret += j - i + 1;
        }
        return ret;
    }

}
