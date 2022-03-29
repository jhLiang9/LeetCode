package Search;

import java.util.*;

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

    /**
     * 2024. 考试的最大困扰度
     *
     * @param answerKey 判断题
     * @param k         可修改的最大数量
     * @return
     */
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(maxChar(answerKey, k, 'T'), maxChar(answerKey, k, 'F'));
    }


    public int maxChar(String answerKey, int k, char ch) {
        int max = 0;
        int n = answerKey.length();
        for (int left = 0, right = 0, sum = 0; right < n; right++) {
            // 不为"T"或者不为"F"的总数
            sum += answerKey.charAt(right) != ch ? 1 : 0;
            // 找到刚好符合操作的位置(将left定位到刚好可操作的位置)
            while (sum > k) {
                sum -= answerKey.charAt(left++) != ch ? 1 : 0;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    /**
     * 1004. 最大连续1的个数 III
     *
     * @param nums int 数组
     * @param k    可操作数
     * @return 最大连续1的个数
     */
    public int longestOnes(int[] nums, int k) {
        return maxLongest(nums, k, 1);
    }

    public int maxLongest(int[] nums, int k, int select) {
        int n = nums.length;
        int max = 0;
        for (int i = 0, j = 0, sum = 0; j < n; j++) {
            sum += nums[j] == select ? 0 : 1;
            while (sum > k) {
                sum -= nums[i++] == select ? 0 : 1;
            }
            max = Math.max(max, j - i + 1);
        }
        return max;
    }


    /**
     * 1876. 长度为三且各字符不同的子字符串
     *
     * @param s 字符串
     * @return 长度为三且各字符不同的子字符串的数量
     */
    public int countGoodSubstrings(String s) {
        int res = 0;
        for (int i = 0, n = 3; i < s.length() - 2; i++, n = 3) {
            HashSet<Character> set = new HashSet<>();
            while (n > 0) {
                set.add(s.charAt(i + n-- - 1));
            }
            res += set.size() / 3;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.countGoodSubstrings("abcd"));
    }


    /**
     * 438. 找到字符串中所有字母异位词
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        if (sLen < pLen) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; ++i) {
            ++sCount[s.charAt(i) - 'a'];
            ++pCount[p.charAt(i) - 'a'];
        }

        if (Arrays.equals(sCount, pCount)) {
            ans.add(0);
        }
        for (int i = 0; i < sLen - pLen; ++i) {
            --sCount[s.charAt(i) - 'a'];
            ++sCount[s.charAt(i + pLen) - 'a'];

            if (Arrays.equals(sCount, pCount)) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
