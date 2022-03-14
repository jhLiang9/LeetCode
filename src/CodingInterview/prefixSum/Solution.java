package CodingInterview.prefixSum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    /**
     * 238. 除自身以外数组的乘积
     * 给你一个整数数组nums，返回 数组answer，其中answer[i]等于nums中除nums[i]之外其余各元素的乘积。
     * <p>
     * 题目数据 保证 数组nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内。
     * <p>
     * 请不要使用除法，且在O(n) 时间复杂度内完成此题。
     * <p>
     *
     * @param nums 给定数组
     * @return 数组answer，其中answer[i]等于nums中除nums[i]之外其余各元素的乘积
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] L = new int[n];
        int[] R = new int[n];
        int[] result = new int[n];
        L[0] = 1;
        R[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            L[i] = nums[i - 1] * L[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            R[i] = R[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < n; i++) {
            result[i] = L[i] * R[i];
        }
        return result;
    }


    /**
     * 剑指 Offer II 012. 左右两边子数组的和相等
     * 给你一个整数数组nums ，请计算数组的 中心下标 。
     * <p>
     * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
     * <p>
     * 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
     * <p>
     * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
     *
     * @param nums 给定数组
     * @return 请计算数组的 中心下标
     */
    public int pivotIndex(int[] nums) {

        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        int[] L = new int[n];
        int[] R = new int[n];
        L[0] = nums[0];
        R[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            L[i] = L[i - 1] + nums[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            R[i] = R[i + 1] + nums[i];
        }
        for (int i = 0; i < n; i++) {
            if (L[i] == R[i]) return i ;
        }
        return -1;
    }


}

/**
 * 给定一个整数数组 nums，处理以下类型的多个查询:
 * <p>
 * 计算索引left和right（包含 left 和 right）之间的 nums 元素的 和 ，其中left <= right
 */

class NumArray {
    int[] sum;

    public NumArray(int[] nums) {
        if (nums.length == 0) return;
        sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] += sum[i - 1] + nums[i];
        }
    }

    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(left,right);
     */
    public int sumRange(int left, int right) {
        if (sum != null) {
            if (left != 0) {
                return sum[right] - sum[left - 1];
            } else {
                return sum[right];
            }

        } else return 0;
    }
}

