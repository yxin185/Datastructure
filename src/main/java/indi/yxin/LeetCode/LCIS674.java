package indi.yxin.LeetCode;

import java.util.PriorityQueue;

/**
 * @Description TODO
 * @Date 2019/11/11 15:13
 **/
public class LCIS674 {
    public static void main(String[] args) {
        Solution674V2 s = new Solution674V2();
        int[] nums = {2,2,2};
        int res = s.findLengthOfLCIS(nums);
        System.out.println(res);
    }
}

class Solution674 {

    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 1)
            return 1;
        // 使用双指针解决
        int res = 0;   // 返回值，最大为 res
        int l = 0;
        int r = l + 1;
        while (l < nums.length - 1) {
            if (r < nums.length && nums[r] > nums[r - 1]) {
                r ++;
            }
            res = Math.max(res, r - l);
            if (r >= nums.length)
                break;
            if (nums[r] <= nums[r - 1]) {
                l = r;
                // 左指针移过来之后，要将右指针向后移动一位
                r ++;
            }
        }
        return res;
    }

}

class Solution674V2 {
    public int findLengthOfLCIS(int[] nums) {
        int res = 0, start = 0;
        for (int i = 0;i < nums.length;++ i) {
            if (i > 0 && nums[i] <= nums[i - 1]) {
                start = i;
            }
            res = Math.max(res, i - start + 1);
        }

        return res;
    }
}
