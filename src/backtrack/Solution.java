package backtrack;

import java.util.*;

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
            res.add(new ArrayList<>(cur));
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

    /**
     * 17. 电话号码的字母组合
     *
     * @param digits 电话号码组合
     * @return 总共的组合
     */
    public List<String> letterCombinations(String digits) {
        if (Objects.equals(digits, "")) return new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();

        String[] numSet = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wyz"};
        for (int i = 2; i <= 9; i++) {
            map.put(String.valueOf(i), numSet[i - 2]);
        }
        List<String> query = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            String s = digits.substring(i, i + 1);
            query.add(map.get(s));
        }
        List<String> res = new ArrayList<>();
        backtrack17(res, query, 0, new StringBuilder());
        return res;
    }

    public void backtrack17(List<String> res, List<String> query, int index, StringBuilder sb) {
        if (index == query.size()) {
            res.add(sb.toString());
            return;
        }
        String options = query.get(index);
        for (int i = 0; i < options.length(); i++) {
            sb.append(options.charAt(i));
            backtrack17(res, query, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    /**
     * 面试题 08.07. 无重复字符串的排列组合
     *
     * @param S 字符串
     * @return 字符串的排列组合
     */
    public String[] permutation(String S) {
        List<String> res = new ArrayList<>();
        backtrack0807(res, 0, S, new StringBuilder());
        return res.toArray(new String[0]);
    }

    public void backtrack0807(List<String> res, int cur, String S, StringBuilder sb) {
        if (cur == S.length()) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < S.length(); i++) {
            if (sb.toString().contains(S.charAt(i) + "")) continue;
            sb.append(S.charAt(i));
            backtrack0807(res, cur + 1, S, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 78. 子集  给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * <p>
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     *
     * @param nums 整数数组 nums
     * @return 解集
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        backtrack78(nums, 0, res, temp);
        return res;
    }

    public void backtrack78(int[] nums, int index, List<List<Integer>> res, List<Integer> temp) {
        if (index == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[index]);
        backtrack78(nums, index + 1, res, temp);
        temp.remove(temp.size() - 1);
    }

    /**
     * 39. 组合总和
     * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     *
     * @param candidates 无重复元素 的整数数组candidates
     * @param target     一个目标整数target
     * @return 找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合
     */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack39(res, target, candidates, 0, new ArrayList<>());
        return res;
    }

    public void backtrack39(List<List<Integer>> res, int target, int[] candidates, int index, List<Integer> output) {
        if (index == candidates.length) return;
        if (target == 0) {
            res.add(new ArrayList<>(output));
            return;
        } else if (output.size() >= 150 || target < 0) {
            return;
        }
        //不选择当前数字
        backtrack39(res, target, candidates, index + 1, output);

        //选择当前数字
        output.add(candidates[index]);
        //后面可能再选,所以index不变
        backtrack39(res, target - candidates[index], candidates, index, output);
        output.remove(output.size() - 1);
    }


    /**
     * 797. 所有可能的路径
     *
     * @param graph 图
     * @return 请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        output.add(0);
        dfs797(res, graph, 0, output);
        return res;
    }

    public void dfs797(List<List<Integer>> res, int[][] graph, int index, List<Integer> output) {
        if (index == graph.length - 1) {
            res.add(new ArrayList<>(output));
            return;
        } else if (index >= graph.length) {
            return;
        }

        for (int j = 0; j < graph[index].length; j++) {
            output.add(graph[index][j]);
            dfs797(res, graph, graph[index][j], output);
            output.remove(output.size() - 1);
        }
    }

    /**
     * 79. 单词搜索
     *
     * @param board m x n 二维字符网格 board
     * @param word  一个字符串单词 word
     * @return 如果 word 存在于网格中，返回 true ；否则，返回 false 。
     */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean ans = backTrack79(board, word, i, j, 0);
                if (ans) return true;
            }
        }
        return false;
    }

    public boolean backTrack79(char[][] board, String word, int i, int j, int k) {
        if (k == word.length()) return true;
        if (i >= board.length || i < 0 || j >= board[i].length || j < 0) return false;
        if (board[i][j] == word.charAt(k)) {
            board[i][j] = '\0';
            boolean res = backTrack79(board, word, i + 1, j, k + 1) || backTrack79(board, word, i - 1, j, k + 1) || backTrack79(board, word, i, j + 1, k + 1) || backTrack79(board, word, i, j - 1, k + 1);
            board[i][j] = word.charAt(k);
            return res;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.allPathsSourceTarget(new int[][]{{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}});
    }
}
