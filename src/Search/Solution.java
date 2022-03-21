package Search;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
    /**
     * 面试题50. 第一个只出现一次的字符
     * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     *
     * @param s 字符串 s
     * @return 第一个只出现一次的字符
     */
    public char firstUniqChar(String s) {
        int[] dict = new int[26];
        for (int i = 0; i < s.length(); i++) {
            dict[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (dict[s.charAt(i) - 'a'] == 1) return s.charAt(i);
        }
        return ' ';
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * <p>
     * 如果数组中不存在目标值 target，返回[-1, -1]。
     * 时间复杂度为 O(log n)
     *
     * @param nums   数组
     * @param target 目标
     * @return 目标值在数组中的开始位置和结束位置
     */
    public int[] searchRange(int[] nums, int target) {
        //二分
        //搜索第一个大于等于target的目标
        int lower = binary(nums, target, true, 0, nums.length - 1);
        //搜索第一个大于target的目标
        int upper = binary(nums, target, false, 0, nums.length - 1) - 1;
        if (lower <= upper && upper < nums.length && nums[lower] == target && nums[upper] == target) {
            return new int[]{lower, upper};
        }

        return new int[]{-1, -1};

    }

    public int binary(int[] nums, int target, boolean lower, int left, int right) {
        int ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}
