package indi.yxin.LeetCode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum40 {
    public static void main(String[] args) {
        Solution40 s = new Solution40();
        int[] candidates = {10,1,2,2,4,7,6,1,5};
        int target = 12;
        List<List<Integer>> res = s.combinationSum2(candidates, target);
        System.out.println(res);
    }
}

class Solution40 {
    private List<List<Integer>> res;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new LinkedList<>();
        if (candidates.length == 0 || target <= 0) {
            return res;
        }
        Arrays.sort(candidates);
        List<Integer> tmp = new LinkedList<>();
        backtrace(candidates, target, 0, tmp);
        return res;
    }

    private void backtrace(int[] candidates, int target, int index, List<Integer> tmp) {
        if (target < 0) {
            return ;
        }
        if (target == 0) {
            res.add(new LinkedList<>(tmp));
            return ;
        }

        for (int i = index;i < candidates.length;i ++) {
            // 要是这一个数和前一个树相等，那么就跳过这个数，因为他的前一个数已经考虑过了
            // 这里的 index 指第一次调用该函数，即进入第一层时的 index ,index = 0
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            tmp.add(candidates[i]);
            // 因为每一个数只能使用一遍，所以数字开始的下标跟着 i + 1
            backtrace(candidates, target - candidates[i], i + 1, tmp);
            tmp.remove(tmp.size() - 1);
        }

    }
}
