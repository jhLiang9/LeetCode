package design;

/**
 * 307. 区域和检索 - 数组可修改
 * Solution 1 前缀和(超时)
 * Solution 2 线段树
 */
public class NumArray {
    int[] nums;
    int[] sum;
    int n;

    public NumArray(int[] nums) {
        this.nums = nums;
        n = nums.length;
        sum = new int[n << 2];
        build(0, n - 1, 0);
    }

    //good
    public void update(int index, int val) {
        nums[index] = val;
        sumUpdate(0, n - 1, 0, val, index);
    }

    //good
    public int sumRange(int left, int right) {
        return query(left, right, 0, n - 1, 0);
    }

    public void sumUpdate(int l, int r, int rt, int val, int index) {
        if (l == r) {
            sum[rt] = val;
            return;
        }
        int mid = (l + r) / 2;
        if (index <= mid) {
            sumUpdate(l, mid, rt * 2 + 1, val, index);
        } else {
            sumUpdate(mid + 1, r, rt * 2 + 2, val, index);
        }
        pushUp(rt);
    }

    public void build(int l, int r, int rt) {
        if (l == r) {
            sum[rt] = nums[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(l, mid, rt * 2 + 1);
        build(mid + 1, r, rt * 2 + 2);
        pushUp(rt);
    }

    public void pushUp(int rt) {
        sum[rt] = sum[rt * 2 + 1] + sum[rt * 2 + 2];
    }

    public int query(int L, int R, int left, int right, int rt) {
        if (L == left && R == right) {
            return sum[rt];
        }
        int mid = (left + right) / 2;
        if (R <= mid) {
            return query(L, R, left, mid, rt * 2 + 1);
        } else if (L > mid) {
            return query(L, R, mid + 1, right, rt * 2 + 2);
        } else {
            return query(L, mid, left, mid, rt * 2 + 1) + query(mid + 1, R, mid + 1, right, rt * 2 + 2);
        }
    }

    public static void main(String[] args) {
        System.out.println((1 << 1) + 2);
        System.out.println((1 << 1) + 1);
        System.out.println(2 << 1 + 2);
    }
}

