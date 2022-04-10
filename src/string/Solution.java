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

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.longestWord(new String[]{"t", "ti", "tig", "tige", "tiger", "e", "en", "eng", "engl", "engli", "englis", "english", "h", "hi", "his", "hist", "histo", "histor", "history"}));
    }
}
