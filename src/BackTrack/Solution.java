package BackTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    /**
     * 22. 括号生成
     *
     * @param n 括号对数
     * @return 所有可能的并且 有效的 括号组合
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack22(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    public void backtrack22(List<String> res, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            res.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack22(res, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack22(res, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }

    }


    /**
     * 46. 全排列
     *
     * @param nums 不含重复数字的数组nums
     * @return 数组的全排列
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }
        backtrack46(res, output, nums, 0);
        return res;
    }

    public void backtrack46(List<List<Integer>> res, List<Integer> cur, int[] nums, int index) {
        if (index == nums.length) {
            res.add(cur);
            return;
        }
        for (int i = index; i < nums.length; i++) {
            Collections.swap(cur, index, i);
            backtrack46(res, cur, nums, index + 1);
            Collections.swap(cur, index, i);
        }
    }

    /**
     * 1863. 找出所有子集的异或总和再求和
     *
     * @param nums 集合
     * @return 子集的异或总和求和
     */
    public int subsetXORSum(int[] nums) {
        List<List<Integer>> children = new ArrayList<>();
        backtrack1863(children, nums, 0, nums.length, new ArrayList<>());
        int sum = 0;
        for (int i = 0; i < children.size(); i++) {
            List<Integer> child = children.get(i);
            int xor = 0;
            for (int j = 0; j < child.size(); j++) {
                xor ^= child.get(j);
            }
            sum += xor;
        }
        return sum;
    }

    public void backtrack1863(List<List<Integer>> children, int[] nums, int cur, int end, List<Integer> output) {
        if (cur == end) {
            children.add(new ArrayList<>(output));
            return;
        }
        output.add(nums[cur]);
        backtrack1863(children, nums, cur + 1, end, output);
        output.remove((Object) nums[cur]);
        backtrack1863(children, nums, cur + 1, end, output);
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        s.subsetXORSum(new int[]{1, 3});

    }
}
