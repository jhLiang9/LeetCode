package CodingInterview.num59;

import java.util.LinkedList;

public class Solution {
    class MaxQueue {
        LinkedList<Integer> queue;
        LinkedList<Integer> stack;

        public MaxQueue() {
            queue = new LinkedList<>();
            stack = new LinkedList<>();
        }

        public int max_value() {
            if (stack.size() != 0) {
                return stack.getFirst();
            }
            return queue.getLast();
        }

        public void push_back(int value) {
            queue.addFirst(value);
        }

        public int pop_front() {
            return queue.getLast();
        }
    }

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
}
