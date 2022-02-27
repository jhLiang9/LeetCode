package pancakeSort;

import java.util.ArrayList;
import java.util.List;

class Solution {
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

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] test = {3, 2, 4, 1};
        s.pancakeSort(test);
    }
}