package contest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Contest309 {
    /**
     * 6167.    检查相同字母间的距离
     * 给你一个下标从 0 开始的字符串 s ，该字符串仅由小写英文字母组成，s 中的每个字母都 恰好 出现 两次 。另给你一个下标从 0 开始、长度为 26 的的整数数组 distance 。
     * <p>
     * 字母表中的每个字母按从 0 到 25 依次编号（即，'a' -> 0, 'b' -> 1, 'c' -> 2, ... , 'z' -> 25）。
     * <p>
     * 在一个 匀整 字符串中，第 i 个字母的两次出现之间的字母数量是 distance[i] 。如果第 i 个字母没有在 s 中出现，那么 distance[i] 可以 忽略 。
     * <p>
     * 如果 s 是一个 匀整 字符串，返回 true ；否则，返回 false 。
     *
     * @param s
     * @param distance
     * @return
     */
    public boolean checkDistances(String s, int[] distance) {
        HashMap<Character, Integer> map = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map.putIfAbsent(c, i);
            if (map.get(c) != i) {
                int d = distance[c - 'a'];
                if (i - map.get(c) - 1 != d) return false;
            }
        }

        return true;
    }

    public int numberOfWays(int startPos, int endPos, int k) {
        int mod = 1000000007;
        int res = 0;
        int dif = endPos - startPos;
        if (dif <= 0 || dif > k || k % dif != 0) return 0;
        if (dif == k) return 1;
        int left = numberOfWays(startPos - 1, endPos, k - 1) % mod;
        if (left != 0) res += left + 1;
        int right = numberOfWays(startPos + 1, endPos, k - 1) % mod;
        if (right != 0) res += right + 1;
        return res;
    }

    /**
     * 6169. 最长优雅子数组
     * 给你一个由 正 整数组成的数组 nums
     * 如果 nums 的子数组中位于 不同 位置的每对元素按位 与（AND）运算的结果等于 0 ，则称该子数组为 优雅 子数组。
     * 返回 最长 的优雅子数组的长度。
     * 子数组 是数组中的一个 连续 部分。
     * 注意：长度为 1 的子数组始终视作优雅子数组。
     *
     * @param nums
     * @return
     */
    public int longestNiceSubarray(int[] nums) {
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
//            if (res > n - i) break;
            int tmp = 0;
            HashMap<Integer, Boolean> map = new HashMap<>();
            for (int k = i; k < n; k++) {
                String s = Integer.toBinaryString(nums[k]);
                boolean end = false;
                int size = s.length();
                for (int j = 0; j < size; j++) {
                    int position = size - j - 1;
                    boolean cur = map.getOrDefault(position, false);
                    char c = s.charAt(j);
                    if (c == '1') {
                        map.put(position, true);
                        if (cur) {
                            res = Math.max(res, tmp);
                            end = true;
                            break;
                        }
                    }
                }
                if (end) break;
                tmp++;
                res = Math.max(res, tmp);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Contest309 contest309 = new Contest309();
        int r = contest309.longestNiceSubarray(new int[]{3, 1, 5, 11});
        System.out.println(r);
    }
}
