package CodingInterview.num31;

/**
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left != right) {
            int temp = nums[left] + nums[right];
            //TODO temp 越界的情况
            if (temp == target) {
                return new int[]{nums[left], nums[right]};
            } else {
                if (temp < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return new int[]{0};

    }
}
