package codinginterview.num58;

/**
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 */

public class Solution {

    public String reverseLeftWords(String s, int n) {
        if (n == 0 || s.length() == 0) return s;
        return s.substring(n) + s.substring(0, n);
    }

    public void reverseString(char[] s) {
        //length
        int n = s.length;
        if (n == 0) return;
        int start = 0;
        int end = n - 1;
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }

    }

    public static void main(String[] args) {

    }
}
