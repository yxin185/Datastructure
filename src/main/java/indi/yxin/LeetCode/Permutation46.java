package indi.yxin.LeetCode;

import java.util.LinkedList;
import java.util.List;

public class Permutation46 {

    public static void main(String[] args) {
        int[] arr = {2,1,3};
        Solution46 s = new Solution46();
        System.out.println(s.permute(arr));

    }
}

class Solution46 {

    public List<List<Integer>> permute(int[] nums) {
        // 记录最终返回的结果
        List<List<Integer>> res = new LinkedList<>();
        if (nums.length == 0) {
            return res;
        }

        boolean[] used = new boolean[nums.length];
//        for (int i = 0;i < used.length;i ++) {
//            used[i] = false;
//        }
        LinkedList<Integer> p = new LinkedList<>();
        generatePermutation(nums, 0, p, res, used);
        return res;
    }
    // p 中保存了一个有index个元素的排列
    // 向这个排列的末尾添加第index+1个元素，获得一个有index+1的元素的排列
    private void generatePermutation(int[] nums, int index, LinkedList<Integer> p, List<List<Integer>> res, boolean[] used) {
        if (index == nums.length) {
            // java 使用的是引用传递，如果直接给 res 添加p
            // 那么 p 一变，res也跟着改变
            // 所以用 p 重新构造一个链表来添加到res中，res就不受p改变的影响
            res.add(new LinkedList<>(p));
            return;
        }

        for (int i = 0;i < nums.length;i ++) {
            if (!used[i]) {
                p.add(nums[i]);
                used[i] = true;
                generatePermutation(nums, index + 1, p, res, used);
                p.remove(p.size() - 1);
                used[i] = false;
            }
        }
    }
}

