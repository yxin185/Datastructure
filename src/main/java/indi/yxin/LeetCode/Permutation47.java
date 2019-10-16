package indi.yxin.LeetCode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Permutation47 {
    public static void main(String[] args) {
        Solution47 s = new Solution47();
        int[] nums = {1,1,2};
        System.out.println(s.permuteUnique(nums));
    }
}

class Solution47 {
    private List<List<Integer>> res;
    // 记录每个数字是否出现过
    private boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new LinkedList<>();
        used = new boolean[nums.length];
        if (nums.length == 0) {
            return res;
        }
        // 先排序，便于后续跳过相同元素
        Arrays.sort(nums);
        List<Integer> tmp = new LinkedList<>();
        backtrace(nums, 0, tmp);
        return res;
    }

    private void backtrace(int[] nums, int index, List<Integer> tmp) {
        if (index == nums.length) {
            res.add(new LinkedList<>(tmp));
            return;
        }

        for (int i = 0;i < nums.length;i ++) {

            if (!used[i]) {
                // 如果出现两个数相等的情况，那么就跳过这一个数
                // 这个地方 第i个数与第i-1个数相等并且used[i - 1]是关键即，第i-1个数被用过了，
                // 所以第i个数就不能再用，只能 continue
                if (i > 0 && nums[i] == nums[i - 1] && used[i - 1]) {
                    continue;
                }
                tmp.add(nums[i]);
                used[i] = true;
                backtrace(nums, index + 1, tmp);
                tmp.remove(tmp.size() - 1);
                used[i] = false;
            }

        }
    }
}
