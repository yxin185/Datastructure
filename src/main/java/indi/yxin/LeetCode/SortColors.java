package indi.yxin.LeetCode;

public class SortColors {
    public static void main(String[] args) {
        System.out.println(" " == null);
        Solution75 s = new Solution75();
        int[] arr = {2,0,2,1,1,0};
        s.sortColors(arr);

        for (int i = 0;i < arr.length;i ++) {
            System.out.print(arr[i] + " ");
        }
    }
}

class Solution75 {
    public void sortColors(int[] nums) {
        // 利用三路快排的 partition 操作
        int zero = -1;  // nums[0,zero] 为0
        int two = nums.length;    // nums[two,nums.length - 1];
        // 中间部分就为1，最终扫描整个数组一遍就能够排序
        for (int i = 0;i < two;) {
            if (nums[i] == 1) {
                i ++;
            } else if (nums[i] == 2) {
                // 将 two - 1位置的元素与i位置元素交换
                swap(nums, -- two, i);
            } else { // nums[i] = 0
                assert nums[i] == 0;
                swap(nums, ++ zero, i ++);
            }
        }
    }
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
