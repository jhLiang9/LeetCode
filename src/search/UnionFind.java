package search;

public class UnionFind {
    int[] id;
    int[] weight;

    /**
     * 根据权重 避免树太高
     */
    public UnionFind(int n) {
        id = new int[n];
        weight = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        if (weight[qRoot] >= weight[pRoot]) {
            id[pRoot] = qRoot;
            weight[qRoot] += weight[pRoot];
        } else {
            id[qRoot] = pRoot;
            weight[pRoot] += weight[qRoot];
        }

    }

    private int root(int i) {
        while (i != id[i]) {
            // 展平树
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }


}
