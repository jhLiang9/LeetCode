package contest;


import java.util.ArrayList;
import java.util.Collections;

public class Contest {


    public int largestInteger(int num) {
        if (num == 0) return num;
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> list0 = new ArrayList<>();   //奇数
        ArrayList<Integer> list1 = new ArrayList<>();   //偶数
        ArrayList<Boolean> list2 = new ArrayList<>();
        while (num != 0) {
            int res = num % 10;
            list2.add(res % 2 == 0);            //记录是否偶数
            if (res % 2 == 0) list1.add(res);
            else list0.add(res);
            num /= 10;
        }
        Collections.sort(list0);
        Collections.sort(list1);
        int a = 0;//ji
        int b = 0;
        while (a != list0.size() || b != list1.size()) {
            if (list2.get(a + b)) {
                sb.append(list1.get(b));
                b++;
            } else {
                sb.append(list0.get(a));
                a++;
            }
        }
        return Integer.parseInt(sb.reverse().toString());
    }

    /**
     * 6038. 向表达式添加括号后的最小结果
     *
     * @param expression num1+num2的格式
     * @return 找出最小值
     */
    public String minimizeResult(String expression) {
        String[] s = expression.split("\\+");
        String left = s[0];
        String right = s[1];

        int min = Integer.parseInt(left) + Integer.parseInt(right);
        String ans = "(" + expression + ")";
        if (left.length() == 1 && right.length() == 1) return ans;
        for (int i = 0; i < left.length(); i++) {
            for (int j = 1; j <= right.length(); j++) {
                int l = i == 0 ? 1 : Integer.parseInt(left.substring(0, i));
                int lr = Integer.parseInt(left.substring(i));
                int rl = j == 0 ? 0 : Integer.parseInt(right.substring(0, j));
                int r = j == right.length() ? 1 : Integer.parseInt(right.substring(j));
                int temp = (lr + rl) * l * r;
                if (temp < min) {
                    min = temp;
                    //两个边界的情况;
                    ans = "";
                    ans = ans + left.substring(0, i) + "(" + left.substring(i) + "+";
                    ans = ans + right.substring(0, j) + ")" + right.substring(j);
                }
            }
        }
        return ans;
    }

    public int maximumProduct(int[] nums, int k) {
        double mod = 10e9 + 7;
        if (k == 0) {
            long ans = 1;
            for (int num : nums) {
                ans *= num;
                ans %= mod;
            }
            return (int) ans;
        } else {
            long max = Long.MIN_VALUE;
            int index = -1;
            for (int i = 0; i < nums.length; i++) {
                //找最大
                long temp = 1;
                nums[i] += 1;
                for (int num : nums) {
                    temp *= num;
                }
                nums[i] -= 1;
                if (temp > max) {
                    max = temp;
                    index = i;
                }
            }
            nums[index] += 1;
            return maximumProduct(nums, k - 1);
        }
    }

    public static void main(String[] args) {
        Contest dp = new Contest();
        System.out.println(dp.maximumProduct(new int[]{92, 36, 15, 84, 57, 60, 72, 86, 70, 43, 16}, 62));

    }
}
