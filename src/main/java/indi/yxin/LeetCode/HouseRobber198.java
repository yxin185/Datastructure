package indi.yxin.LeetCode;

public class HouseRobber198 {

    private static int[] memo;
    public static int rob(int[] nums) {
        // 递归，记忆化搜索
        memo = new int[nums.length];
        for (int i = 0;i < memo.length;i ++) {
            memo[i] = -1;
        }
        return tryRob(nums, 0);
    }

    private static int tryRob(int[] nums, int index) {
        if (index >= nums.length)
            return 0;
        // 说明这个地方的值已经被计算过了
        if (memo[index] != -1)
            return memo[index];

        int res = 0;
        for (int i = index;i < nums.length;i ++) {
            res = Math.max(res, nums[i] + tryRob(nums, i + 2));
        }
        memo[index] = res;
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        int res = HouseRobber198.rob(nums);
        System.out.println(res);
    }
}
