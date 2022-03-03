package CodingInterview.num59;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
public class Solution {
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
                    while (maxQueue.getFirst()<value){
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
                    maxQueue.remove((Object)result);
                }
                queue.removeFirst();
                return result;
            } else {
                return -1;
            }

        }
    }
}
