package CodingInterview.prefixSum;

public class Solution {

}

/**
 * 给定一个整数数组 nums，处理以下类型的多个查询:
 *
 * 计算索引left和right（包含 left 和 right）之间的 nums 元素的 和 ，其中left <= right
 *
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

    public int sumRange(int left, int right) {
        if (sum != null) {
            if (left != 0) {
                return sum[right] - sum[left-1];
            } else {
                return sum[right];
            }

        } else return 0;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */