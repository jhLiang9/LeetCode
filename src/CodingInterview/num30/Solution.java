package CodingInterview.num30;

import java.util.LinkedList;
import java.util.Objects;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 */

class MinStack {
    LinkedList<Integer> stack;
    LinkedList<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
    }

    public void push(int x) {
        if (minStack.size() == 0 || x <= minStack.getFirst()) {
            minStack.addFirst(x);
        }
        stack.addFirst(x);
    }

    public void pop() {
        if (minStack.size() == 0) {
            stack.removeFirst();
            return;
        }
        if (Objects.equals(stack.getFirst(), minStack.getFirst())) {
            stack.removeFirst();
            minStack.removeFirst();
        } else {
            stack.removeFirst();
        }

    }

    public int top() {
        return stack.getFirst();
    }

    public int min() {
        if (minStack.size() > 0) {
            return minStack.getFirst();
        } else {
            return stack.getFirst();
        }

    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(512);
        stack.push(-1024);
        stack.push(-1024);
        stack.push(512);
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.min());
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */