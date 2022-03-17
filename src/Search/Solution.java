package Search;

import java.util.HashMap;

public class Solution {
    /**
     * 面试题50. 第一个只出现一次的字符
     * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     *
     * @param s 字符串 s
     * @return 第一个只出现一次的字符
     */
    public char firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int[] dict = new int[26];
        for (int i = 0; i < s.length(); i++) {
            dict[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (dict[s.charAt(i) - 'a'] == 1) return s.charAt(i);
        }
        return ' ';
    }
}
