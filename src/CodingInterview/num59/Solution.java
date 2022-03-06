package CodingInterview.num59;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;


public class Solution {
    /**
     * Your MaxQueue object will be instantiated and called as such:
     * MaxQueue obj = new MaxQueue();
     * int param_1 = obj.max_value();
     * obj.push_back(value);
     * int param_3 = obj.pop_front();
     */
    class MaxQueue {
        LinkedList<Integer> queue;
        LinkedList<Integer> maxQueue;

        public MaxQueue() {
            queue = new LinkedList<>();
            maxQueue = new LinkedList<>();
        }

        public int max_value() {
            if (maxQueue.size() != 0) {
                return maxQueue.getLast();
            } else if (queue.size() != 0) {
                return queue.getFirst();
            } else {
                return -1;
            }

        }

        public void push_back(int value) {
            queue.addLast(value);

            if (maxQueue.size() != 0) {
                if (value >= maxQueue.getLast()) {
                    maxQueue.addLast(value);
                } else {
                    while (maxQueue.getFirst() < value) {
                        maxQueue.removeFirst();
                    }
                    maxQueue.addFirst(value);
                }
            } else {
                maxQueue.addFirst(value);
            }
        }

        public int pop_front() {
            if (queue.size() != 0) {
                int result = queue.getFirst();
                if (maxQueue.size() != 0) {
                    maxQueue.remove((Object) result);
                }
                queue.removeFirst();
                return result;
            } else {
                return -1;
            }

        }
    }

    /**
     * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
     */
    //Solution 1 超时
//    public int[] maxSlidingWindow(int[] nums, int k) {
//        if (k == 1) return nums;
//        int[] result = new int[nums.length - k + 1];
//        for (int i = 0; i < nums.length - k + 1; i++) {
//            int max = Integer.MIN_VALUE;
//            for (int j = i; j < k + i; j++) {
//                if (nums[j] > max) {
//                    max = nums[j];
//                }
//            }
//            result[i] = max;
//        }
//        return result;
//    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 1) return nums;
        MaxQueue queue = new MaxQueue();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            queue.push_back(nums[i]);
        }
        result[0] = queue.max_value();
        for (int i = 1; i < result.length; i++) {
            queue.pop_front();
            queue.push_back(nums[i + k - 1]);
            result[i] = queue.max_value();
        }

        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        for (int i : s.maxSlidingWindow(nums, k)) System.out.println(i);
        System.out.println();
    }

}
