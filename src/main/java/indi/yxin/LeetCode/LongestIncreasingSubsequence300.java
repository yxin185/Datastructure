package indi.yxin.LeetCode;

public class LongestIncreasingSubsequence300 {
    public static void main(String[] args) {
        Solution300 s = new Solution300();
        int[] nums = {};
        System.out.println(s.lengthOfLIS(nums));
    }
}

class Solution300 {
    public int lengthOfLIS(int[] nums) {

        if (nums.length == 0)
            return 0;

        // nums[i] 表示以 i 结尾的最长上升子序列的长度
        int[] memo = new int[nums.length];
        for (int i = 0;i < memo.length;i ++) {
            memo[i] = 1;
        }

        for (int i = 1;i < nums.length; i++) {
            for (int j = 0;j < i;j ++) {
                // 说明 i 能够接到 j 后面
                // 那么以 i 结尾的最长序列就与以 j 结尾的最长序列相关了
                if (nums[j] < nums[i]) {
                    memo[i] = Math.max(memo[i], 1 + memo[j]);
                }
            }
        }

        int res = 0;
        for (int num : memo) {
            if (num > res)
                res = num;
        }

        return res;
    }
}
