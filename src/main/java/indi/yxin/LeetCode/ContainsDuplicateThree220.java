package indi.yxin.LeetCode;

import java.util.TreeSet;

/**
 * @Description TODO
 * @Date 2019/11/12 21:42
 **/
public class ContainsDuplicateThree220 {
    public static void main(String[] args) {
        Solution220 s = new Solution220();
        int[] nums = {0, Integer.MAX_VALUE};
        int k = 1, t = Integer.MAX_VALUE;
        System.out.println(s.containsNearbyAlmostDuplicate(nums, k , t));
    }
}

class Solution220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 时间复杂度 o(n log n) TreeSet 使用二分查找，复杂度为 o(log n)
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0;i < nums.length;i ++) {
            // 因为 TreeSet 是有序的，假如大于等于 nums[i] - t 的绝对值的最小整数存在
            // 并且这个值 小于等于 nums[i] + t
            // 为避免数值越界，采用长整型
            if (set.ceiling( (long)nums[i] - (long)t) != null) {
                if (set.contains( set.ceiling( (long)nums[i] - (long)t ) ) &&
                        set.ceiling( (long)nums[i] - (long)t ) <= (long)nums[i] + (long)t) {
                    return true;
                }
            }

            set.add((long)nums[i]);
            // 控制滑动窗口大小最大为k
            if (set.size() > k) {
                set.remove((long)nums[i - k]);
            }
        }
        return false;
    }
}
