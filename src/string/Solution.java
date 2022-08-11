package string;

import java.util.*;

public class Solution {
    /**
     * 720. 词典中最长的单词
     * https://leetcode-cn.com/problems/longest-word-in-dictionary/
     * 给出一个字符串数组words 组成的一本英语词典。返回words 中最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。
     * <p>
     * 若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
     *
     * @param words 字符串
     * @return 最长单词
     */
    public String longestWord(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));

        String result = "";
        int max = 0;
        for (String word : words) {
            boolean flag = true;
            StringBuilder sb = new StringBuilder();
            //判断前缀是否都在set中
            for (int i = 0; i < word.length(); i++) {
                sb.append(word.charAt(i));
                if (!set.contains(sb.toString())) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                continue;
            }
            //判断是否最大
            if (word.length() > max) {
                max = word.length();
                result = word;
            } else if (word.length() == max) {
                for (int i = 0; i < word.length(); i++) {
                    char c1 = word.charAt(i);
                    char c2 = result.charAt(i);
                    if (c1 < c2) {
                        result = word;
                        break;
                    } else if (c1 > c2) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 804. 唯一摩尔斯密码词
     *
     * @param words 字符串数组
     * @return 对 words 中所有单词进行单词翻译，返回不同 单词翻译 的数量。
     */
    public int uniqueMorseRepresentations(String[] words) {
        HashSet<String> set = new HashSet<>();
        String[] codes = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                sb.append(codes[word.charAt(i) - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }

    /**
     * @param s 字符串
     * @return 结果
     */
    public String makeLargestSpecial(String s) {
        if (s.length() <= 2) {
            return s;
        }
        int cnt = 0, left = 0;
        List<String> subs = new ArrayList<String>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '1') {
                ++cnt;
            } else {
                --cnt;
                if (cnt == 0) {
                    subs.add("1" + makeLargestSpecial(s.substring(left + 1, i)) + "0");
                    left = i + 1;
                }
            }
        }

        Collections.sort(subs, (a, b) -> b.compareTo(a));
        StringBuilder ans = new StringBuilder();
        for (String sub : subs) {
            ans.append(sub);
        }
        return ans.toString();
    }

    /**
     * 640. 求解方程
     * 思路: 模拟, 将等号右边的都转为负数
     *
     * @param equation 方程
     * @return
     */
    public String solveEquation(String equation) {
        //factor 参数, val 其余值
        int factor = 0, val = 0;
        int index = 0, n = equation.length(), sign1 = 1; // 等式左边默认系数为正
        while (index < n) {
            if (equation.charAt(index) == '=') {
                sign1 = -1; // 等式右边默认系数为负
                index++;
                continue;
            }

            int sign2 = sign1, number = 0;
            boolean valid = false; // 记录 number 是否有效
            if (equation.charAt(index) == '-' || equation.charAt(index) == '+') { // 去掉前面的符号
                sign2 = (equation.charAt(index) == '-') ? -sign1 : sign1;
                index++;
            }
            while (index < n && Character.isDigit(equation.charAt(index))) {
                number = number * 10 + (equation.charAt(index) - '0');
                index++;
                valid = true;
            }

            if (index < n && equation.charAt(index) == 'x') { // 变量
                factor += valid ? sign2 * number : sign2;
                index++;
            } else { // 数值
                val += sign2 * number;
            }
        }
        StringBuilder s = new StringBuilder();
        if (factor == 0) {
            return val == 0 ? "Infinite solutions" : "No solution";
        }
        return "x=" + (-val / factor);
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        s.makeLargestSpecial("11011000");
        System.out.println(s.solveEquation("x+5-3+x=6+x-2"));

        System.out.println(s.longestWord(new String[]{"t", "ti", "tig", "tige", "tiger", "e", "en", "eng", "engl", "engli", "englis", "english", "h", "hi", "his", "hist", "histo", "histor", "history"}));
    }
}
