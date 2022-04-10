package bitoperation;

public class Solution {
    /**
     * 137. 只出现一次的数字 II
     * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
     *
     * @param nums 数组
     * @return 只出现一次的数字
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                total += (num >> i) & 1;
            }
            if (total % 3 != 0) {
                res |= (1 << i);
            }
        }
        return res;
    }

//    public int singleNumber(int[] nums) {
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int num : nums) {
//            map.put(num, map.getOrDefault(num, 0) + 1);
//        }
//        for (int key : map.keySet()) {
//            if (map.get(key) == 1) return key;
//        }
//        return -1;
//    }

}
