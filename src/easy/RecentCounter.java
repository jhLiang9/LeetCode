package easy;

import java.util.ArrayList;

/**
 * 933. 最近的请求次数
 */
public class RecentCounter {
    int index;
    ArrayList<Integer> request;

    public RecentCounter() {
        index = 0;
        request = new ArrayList<>();
    }

    public int ping(int t) {
        request.add(t);
        while (t - 3000 > request.get(index)) {
            index++;
        }
        return request.size() - index;
    }
}