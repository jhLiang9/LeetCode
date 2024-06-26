package game;

import java.util.LinkedList;
import java.util.Queue;

public class Game {

    /**
     * 2038. 如果相邻两个颜色均相同则删除当前颜色
     * 总共有 n个颜色片段排成一列，每个颜色片段要么是'A'要么是'B'。给你一个长度为n的字符串colors，其中colors[i]表示第i个颜色片段的颜色。
     * <p>
     * Alice 和 Bob 在玩一个游戏，他们 轮流从这个字符串中删除颜色。Alice 先手。
     * <p>
     * 如果一个颜色片段为 'A'且 相邻两个颜色都是颜色 'A'，那么 Alice 可以删除该颜色片段。Alice不可以删除任何颜色'B'片段。
     * 如果一个颜色片段为 'B'且 相邻两个颜色都是颜色 'B'，那么 Bob 可以删除该颜色片段。Bob 不可以删除任何颜色 'A'片段。
     * Alice 和 Bob 不能从字符串两端删除颜色片段。
     * 如果其中一人无法继续操作，则该玩家 输掉游戏且另一玩家 获胜。
     * 假设 Alice 和 Bob 都采用最优策略，如果 Alice 获胜，请返回true，否则 Bob 获胜，返回false.
     *
     * @param colors 颜色
     * @return Alice是否获胜
     */
    public boolean winnerOfGame(String colors) {
        int resultA = 0, resultB = 0;
        for (int i = 0; i < colors.length(); i++) {
            char c = colors.charAt(i);
            if (i + 1 < colors.length() && i - 1 >= 0 && colors.charAt(i - 1) == c && colors.charAt(i + 1) == c) {
                if (c == 'A') resultA++;
                else resultB++;
            }
        }
        return resultA > resultB;
    }


    public int findTheWinner(int n, int k) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < n; i++) queue.add(i);
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < k; j++) queue.add(queue.poll());
            queue.poll();
        }
        return queue.poll();
    }

}
