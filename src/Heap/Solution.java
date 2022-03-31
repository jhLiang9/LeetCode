package Heap;

import java.util.*;

public class Solution {
    /**
     * 1606. 找到处理最多请求的服务器
     *
     * @param k
     * @param arrival
     * @param load
     * @return
     */
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        List<Integer> ans = new ArrayList<>();
        int[] servers = new int[k];
        int[] counts = new int[k];
        int minServer = 0;
        for (int i = 0; i < arrival.length; i++) {
            servers[minServer] += load[i];
            counts[minServer]++;
            for (int j = 0, min = Integer.MAX_VALUE; j < k; j++) {
                if (servers[j] < min) {
                    minServer = j;
                }
            }
        }

        for (int i = 0, count = Integer.MIN_VALUE; i < k; i++) {
            if (counts[i] >= count) {
//                ans.add()
            }
        }
        return ans;
    }

    public int maxProduct(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i : nums) queue.offer(i);
        return (queue.poll() - 1) * (queue.poll() - 1);
    }

    /**
     * 1337. 矩阵中战斗力最弱的 K 行
     *
     * @param mat 矩阵
     * @param k   k行
     * @return 矩阵中战斗力最弱的 K 行
     */
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        List<int[]> power = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            int l = 0, r = n - 1, pos = -1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (mat[i][mid] == 0) {
                    r = mid - 1;
                } else {
                    pos = mid;
                    l = mid + 1;
                }
            }
            //i 队列行数
            power.add(new int[]{pos + 1, i});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                if (pair1[0] != pair2[0]) {
                    return pair1[0] - pair2[0];
                } else {
                    return pair1[1] - pair2[1];
                }
            }
        });
        for (int[] pair : power) {
            pq.offer(pair);
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = pq.poll()[1];
        }
        return ans;
    }

    /**
     * 剑指 Offer II 060. 出现频率最高的 k 个数字
     *
     * @param nums 数字列表
     * @param k k 个数字
     * @return 出现频率最高的 k 个数字
     */
    public int[] topKFrequent(int[] nums, int k) {
        int[] ans = new int[k];
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int key : map.keySet()) {
            queue.add(new int[]{key, map.get(key)});
        }
        while (k-- != 0) {
            ans[count++] = queue.peek()[0];
            queue.remove(queue.peek());
        }
        return ans;
    }

    /**
     * 451. 根据字符出现频率排序
     * @param s 字符串s
     * @return 排序后字符串
     */
    public String frequencySort(String s) {
        StringBuilder ans = new StringBuilder();
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        List<Character> list = new ArrayList<>(map.keySet());
        list.sort((a, b) -> map.get(b) - map.get(a));
        for (char c : list) {
            ans.append(String.valueOf(c).repeat(Math.max(0, map.get(c))));
        }
        return ans.toString();
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        s.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
    }

}
