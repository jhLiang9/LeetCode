package contest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class Contest308 {
    /**
     * 6160. 和有限的最长子序列 显示英文描述
     * 通过的用户数2858
     * 尝试过的用户数3210
     * 用户总通过次数2866
     * 用户总提交次数3712
     * 题目难度Easy
     * 给你一个长度为 n 的整数数组 nums ，和一个长度为 m 的整数数组 queries 。
     * <p>
     * 返回一个长度为 m 的数组 answer ，其中 answer[i] 是 nums 中 元素之和小于等于 queries[i] 的 子序列 的 最大 长度  。
     * <p>
     * 子序列 是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。
     *
     * @param nums
     * @param queries
     * @return
     */
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int sum = 0;
            int count = 0;
            for (int num : nums) {
                sum += num;
                if (sum <= queries[i]) count++;
                else break;
            }
            res[i] = count;
        }
        return res;
    }

    /**
     * 6161. 从字符串中移除星号 显示英文描述
     * <p>
     * 给你一个包含若干星号 * 的字符串 s 。
     * <p>
     * 在一步操作中，你可以：
     * <p>
     * 选中 s 中的一个星号。
     * 移除星号 左侧 最近的那个 非星号 字符，并移除该星号自身。
     * 返回移除 所有 星号之后的字符串。
     * <p>
     * 注意：
     * <p>
     * 生成的输入保证总是可以执行题面中描述的操作。
     * 可以证明结果字符串是唯一的。
     */

    public String removeStars(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != '*') {
                sb.append(c);
            } else {
                if (sb.length() > 0) sb.delete(sb.length() - 1, sb.length());
            }
        }
        return sb.toString();
    }

    /**
     * 6162. 收集垃圾的最少总时间 显示英文描述
     * 通过的用户数3395
     * 尝试过的用户数3434
     * 用户总通过次数3442
     * 用户总提交次数3762
     * 题目难度Medium
     * 给你一个下标从 0 开始的字符串数组 garbage ，其中 garbage[i] 表示第 i 个房子的垃圾集合。garbage[i] 只包含字符 'M' ，'P' 和 'G' ，但可能包含多个相同字符，每个字符分别表示一单位的金属、纸和玻璃。垃圾车收拾 一 单位的任何一种垃圾都需要花费 1 分钟。
     * <p>
     * 同时给你一个下标从 0 开始的整数数组 travel ，其中 travel[i] 是垃圾车从房子 i 行驶到房子 i + 1 需要的分钟数。
     * <p>
     * 城市里总共有三辆垃圾车，分别收拾三种垃圾。每辆垃圾车都从房子 0 出发，按顺序 到达每一栋房子。但它们 不是必须 到达所有的房子。
     * <p>
     * 任何时刻只有 一辆 垃圾车处在使用状态。当一辆垃圾车在行驶或者收拾垃圾的时候，另外两辆车 不能 做任何事情。
     * <p>
     * 请你返回收拾完所有垃圾需要花费的 最少 总分钟数
     *
     * @param garbage 垃圾
     * @param travel  通行事件
     * @return
     */
    public int garbageCollection(String[] garbage, int[] travel) {
        int res = 0;
        //M ->P ->G
        int n = garbage.length;
        int[] max = new int[]{0, 0, 0};
        int sum = 0;
        for (String s : garbage) sum += s.length();
        for (int i = n - 1; i > 0; i--) {
            HashSet<Character> set = new HashSet<>();
            for (int j = 0; j < garbage[i].length(); j++) set.add(garbage[i].charAt(j));
            for (char c : set) {
                if (c == 'M') max[0] = Math.max(i, max[0]);
                if (c == 'P') max[1] = Math.max(i, max[1]);
                if (c == 'G') max[2] = Math.max(i, max[2]);
            }
        }
        int travelSum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <= max[i]; j++) {
                travelSum += travel[j];
            }
        }
        res += sum;
        res += travelSum;
        return res;
    }
}
