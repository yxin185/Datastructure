package indi.yxin.LeetCode;

public class Knapsack01 {
    public static void main(String[] args) {
        Solution01 s = new Solution01();
        DP dp = new DP();
        int[] v = {6,10,12};
        int[] w = {1,2,3};
        int C = 5;
//        int res1 = s.knapsack01(w,v,C);
        int res2 = dp.knapsack01(w,v,C);
        System.out.println(res2);
    }

}
// 使用记忆化搜索递归完成
class Solution01 {
    // 因为递归过程存在大量的重复计算，所以使用记忆化搜索
    private int[][] memo;
    /**
     *
     * @param w 物品重量
     * @param v 物品价值
     * @param C 背包最大容量
     * @return 返回最大价值
     */
    public int knapsack01(int[] w, int[] v, int C) {
        if (C <= 0)
            return 0;

        int n = w.length;
        memo = new int[n][C + 1];
        for (int i = 0;i < memo.length;i ++) {
            for (int j = 0;j < memo[0].length;j ++) {
                memo[i][j] = -1;
            }
        }

        return bestValue(w, v, n - 1, C);
    }

    /**
     * 用[0...index] 的物品，填充容积为C的背包
     * @param w
     * @param v
     * @param index
     * @param c
     * @return
     */
    private int bestValue(int[] w, int[] v, int index, int c) {
        // 递归终止条件
        if (index <= 0 || c <= 0)
            return 0;

        // 不为-1，说明之前已经计算过了
        if (memo[index][c] != -1) {
            return memo[index][c];
        }

        int res = bestValue(w, v, index - 1, c);
        if (c >= w[index]) {
            // 说明index号物品还能够放入背包
            res = Math.max(res, v[index] + bestValue(w, v, index - 1, c - w[index]));
        }
        memo[index][c] = res;
        return res;
    }
}
// 使用动态规划完成
// 搞清楚状态是如何转移的
// 状态转移方程   F(n,C)考虑将n个物品放进最大容量为C的背包，使得价值最大
// F(i,C) = max( F(i-1,C) , v[i] + F(i-1,C-w[i]))
// 第i行只依赖于第i-1行，所以优化的话只用2行记录即可
// 时间空间复杂度 O(n * C)
class DP {
    public int knapsack01(int[] w, int[] v, int C) {

        assert (w.length == v.length);
        int n = w.length;
        if (n == 0)
            return 0;
        // 记录自底向上的结果
        // 因为容量从0-C变化，所以需要C + 1列来记录
        int[][] memo = new int[n][C + 1];
        for (int i = 0;i < n;i ++) {
            for (int j = 0;j <= C;j ++) {
                memo[i][j] = -1;
            }
        }
        // 先将第一行初始化
        // 因为第一行相当于只有一个物品，所以最多也就只能放这一个物品的价值进去
        for (int j = 0;j <= C;j ++) {
            memo[0][j] = (j >= w[0] ? v[0] : 0);
        }

        // 开始动态规划
        for (int i = 1;i < n;i ++) {
            for (int j = 0;j <= C;j ++) {
                // 第一种情况:第i个物品不能放进背包
                memo[i][j] = memo[i - 1][j];
                // 第二种情况：第i个物品能够放进背包。这一句需要好好考虑一下
                // memo[i - 1][j - w[i]]代表的是在没有放入i号物品之前的最大价值,j-w[i]代表没有放入i号物品的容量
                if (j >= w[i])
                    memo[i][j] = Math.max(memo[i][j], v[i] + memo[i - 1][j - w[i]]);
            }
        }
        return memo[n - 1][C];
    }
}