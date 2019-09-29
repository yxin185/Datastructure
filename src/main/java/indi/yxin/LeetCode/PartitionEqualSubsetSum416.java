package indi.yxin.LeetCode;

public class PartitionEqualSubsetSum416 {

    public static void main(String[] args) {
//        Solution416 s = new Solution416();
        DP416 dp = new DP416();
        int[] nums = {1,2,5};
        System.out.println(dp.canPartition(nums));
    }
}

/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 */
class Solution416 {
    private int[][] memo;
    public boolean canPartition(int[] nums) {
        if (nums.length == 0)
            return false;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        System.out.println("sum = " + sum);
        // 如果和不能平分，则结果肯定为 false
        if (sum % 2 != 0)
            return false;

        // 初始化一个记忆数组
        memo = new int[nums.length][sum / 2 + 1];
        for (int i = 0;i < memo.length;i ++) {
            for (int j = 0;j < memo[0].length;j ++) {
                memo[i][j] = -1;
            }
        }

        return tryPartition(nums, nums.length - 1, sum / 2);
    }

    /**
     * 使用 nums[0,index]，是否可以完全填满一个容量为 sum 的背包。记忆化搜索
     * @param nums
     * @param index
     * @param sum
     * @return
     */
    private boolean tryPartition(int[] nums, int index, int sum) {
        // 递归终止条件
        if (sum == 0)
            return true;
        // 说明不能填满
        if (sum < 0 || index < 0)
            return false;

        // memo[i][C] 代表用索引为 [0,i] 的这些元素是否可以完全填充一个容量为 C 的背包
        // -1 代表未计算过；0 代表不能填充，1代表可以填充。
        if (memo[index][sum] != -1){
            return memo[index][sum] == 1;
        }

        // 开始递归调用
        // 第一行：第 index 号物品还没有放进去，这个背包就装满了
        // 第二行：第 index 号物品放进去之后，这个背包就装满了
        memo[index][sum] = (tryPartition(nums, index - 1, sum) ||
                tryPartition(nums, index - 1, sum - nums[index])) ? 1 : 0;

        return memo[index][sum] == 1;
    }
}

class DP416 {

    public boolean canPartition(int[] nums) {
        if (nums.length == 0)
            return false;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        System.out.println("sum = " + sum);
        // 如果和不能平分，则结果肯定为 false
        if (sum % 2 != 0)
            return false;

        // 开始动态规划过程
        int n = nums.length;
        int C = sum / 2;

        boolean[] memo = new boolean[C + 1];
        // 首先初始化 memo
        for (int j = 0;j <= C;j ++) {
            memo[j] = (nums[0] == j);
        }

        // nums[i] 相当于第 i 个物品的重量
        for (int i = 1;i < n;i ++) {
            for (int j = C;j >= nums[i];j --) {
                memo[j] = memo[j] || memo[j - nums[i]];
            }
        }

        return memo[C];
    }
}