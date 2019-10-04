package indi.yxin.LeetCode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum39 {
    public static void main(String[] args) {
        Solution39 s = new Solution39();
        int[] candidates = {2,3,6,7};
        int target = 7;
        List<List<Integer>> res = s.combinationSum(candidates, target);
        System.out.println(res);
    }
}

class Solution39 {
    private List<List<Integer>> res;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new LinkedList<>();
        // 先对candidates 进行一个排序
        Arrays.sort(candidates);
        if (candidates.length == 0 || target <= 0) {
            return res;
        }
        // 将临时结果保存在 tmp 中
        List<Integer> tmp = new LinkedList<>();
        generateCombinationSum(candidates, target, 0, tmp);
        return res;
    }

    /**
     * 生成满足条件的组合数和
     * @param candidates
     * @param target
     * @param index：从candidate第 index 个数开始遍历数组，避免重复
     * @param tmp
     */
    private void generateCombinationSum(int[] candidates, int target, int index, List<Integer> tmp) {
        if (target < 0) {
            return ;
        }
        if (target == 0) {
            // 引用传递会导致问题
            res.add(new LinkedList<>(tmp));
            return;
        }

        for (int i = index;i < candidates.length;i ++) {
            tmp.add(candidates[i]);
            generateCombinationSum(candidates, target - candidates[i], i, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
