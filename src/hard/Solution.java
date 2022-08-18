package hard;

import java.util.HashMap;
import java.util.Map;

public class Solution {


    /**
     * ****[1224. 最大相等频率](https://leetcode.cn/problems/maximum-equal-frequency/) 哈希表**
     * 以nums[i]结尾的数组前缀符合要求的充要条件为满足以下三个条件之一
     * 1. 最大出现次数maxFreq=1：那么所有数的出现次数都是一次，随意删除一个数既可符合要求。
     * 2. 所有数出现次数都是 maxFreq 或 maxFreq - 1，并且最大出现次数的数只有一个：删除一个最大出现次数的数，那么所有数出现次数都是maxFreq−1。
     * 3. 除开一个数，其他所有数出现次数都是 maxFreq，并且该数的出现次数为 1：直接删除出现次数为 11 的数，那么所有数出现次数都是 maxFreq。
     *
     * @param nums
     * @return
     */
    public int maxEqualFreq(int[] nums) {
        // freq记录出现次数f的数目freq[f]
        Map<Integer, Integer> freq = new HashMap<>();
        //count 记录数字x出现的次数f
        Map<Integer, Integer> count = new HashMap<>();
        int res = 0, maxFreq = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count.getOrDefault(nums[i], 0) > 0) {
                freq.put(count.get(nums[i]), freq.get(count.get(nums[i])) - 1);
            }
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            maxFreq = Math.max(maxFreq, count.get(nums[i]));
            freq.put(count.get(nums[i]), freq.getOrDefault(count.get(nums[i]), 0) + 1);
            boolean ok = maxFreq == 1 ||
                    freq.get(maxFreq) * maxFreq + freq.get(maxFreq - 1) * (maxFreq - 1) == i + 1 && freq.get(maxFreq) == 1 ||
                    freq.get(maxFreq) * maxFreq + 1 == i + 1 && freq.get(1) == 1;
            if (ok) {
                res = Math.max(res, i + 1);
            }
        }
        return res;
    }
}


