package sort;

import java.util.ArrayList;
import java.util.List;

public class Sort {
    // 选择排序 O(N^2/2)

    /**
     * 选择排序，O(N^2/2)，每次都选择数组中最小的元素。将它和数组第一个元素交换位置。如此反复。
     *
     * @param nums 数组
     * @return
     */
    public int[] selectSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            //找到最小的
            int min = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[min];
            nums[min] = temp;
        }
        return nums;
    }

    /**
     * 插入排序，选择当前，插入到前面
     *
     * @param nums 数组
     * @return 排序结果
     */
    public int[] insertSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                swap(nums, j - 1, j);
            }
        }
        return nums;
    }

    public void quickSort(int[] nums, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(nums, lo, hi);
        quickSort(nums, lo, j);
        quickSort(nums, j + 1, hi);
    }

    private int partition(int[] nums, int lo, int hi) {

        int i = lo, j = hi + 1;
        int v = nums[lo]; // 切分元素
        while (true) {
            while (less(nums[i++], v)) if (i == hi) break; // 切分元素的 右边
            while (less(v, nums[--j])) if (j == lo) break; // 切分元素的 左边
            if (i >= j) break;
            exch(nums, i, j);
        }
        exch(nums, lo, j);
        // j就是当前切分的位置
        return j;
    }

    public List<Integer> pancakeSort(int[] arr) {
        ArrayList<Integer> result = new ArrayList<>();
        int size = arr.length;
        while (size != 0) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == size) {
                    reverse(arr, i + 1);
                    reverse(arr, size);
                    result.add(i + 1);
                    result.add(size);
                    size--;
                    break;
                }
            }
        }
        return result;
    }

    public void reverse(int[] arr, int n) {
        for (int i = 0; i < n / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = temp;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void exch(int[] nums, int i, int j) {
        swap(nums, i, j);
    }

    private boolean less(int i, int j) {
        return i < j;
    }

    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] nums = new int[]{1, 5, 6, 3, 2, 4};
        sort.quickSort(nums, 0, nums.length - 1);
        for (int num : nums) System.out.println(num);
    }
}
