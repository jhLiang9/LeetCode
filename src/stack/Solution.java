package stack;

import java.util.ArrayDeque;
import java.util.Deque;


public class Solution {
    /**
     * 剑指 Offer II 038. 每日温度
     * 请根据每日 气温 列表 temperatures ，重新生成一个列表，要求其对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     * 解法:单调栈
     *
     * @param temperatures 气温数组
     * @return 结果数组
     */
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] ans = new int[temperatures.length];
        int index = 0;
        while (index < temperatures.length) {
            // 当栈为空，或者当前元素 <= 栈顶元素，则将当前元素的索引进栈，形成栈底到栈顶的递减栈
            // 同时，将 i 指向下一天的温度
            if (stack.isEmpty() || temperatures[stack.peek()] >= temperatures[index]) {
                stack.push(index++);
            } else { // 如果当前元素 > 栈顶元素，则将栈顶索引出栈，说明找到了比栈顶索引更高的温度，获取对应的天数。
                int top = stack.pop();
                ans[top] = index - top;
            }
        }
        return ans;
    }

    /**
     * 150. 逆波兰表达式求值
     * 简单题
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> numStack = new ArrayDeque<>();
        for (String s : tokens) {
            char c = s.charAt(s.length()-1);
            if (Character.isDigit(c)) {
                numStack.push(Integer.parseInt(s));
            } else {
                int a = numStack.pop();
                int b = numStack.pop();
                switch (c) {
                    case '+':
                        numStack.push(a + b);
                        break;
                    case '-':
                        numStack.push(b - a);
                        break;
                    case '*':
                        numStack.push(b * a);
                        break;
                    case '/':
                        numStack.push(b / a);
                        break;
                }
            }
        }
        return numStack.pop();
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
//        for (int result : solution.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})) {
//            System.out.println(result);
//        }
        solution.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"});
    }

}
