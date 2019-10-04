package indi.yxin.LeetCode;

import java.util.LinkedList;
import java.util.List;

// 还没有解决
public class CombinationSum216 {


    public static void main(String[] args) {
//        Solutiono216 s = new Solutiono216();
        Solution216V2 s2 = new Solution216V2();
        int k = 3;
        int n = 7;
//        System.out.println("第一种：" + s.combinationSum3(k,n));
        System.out.println("第二种：" + s2.combinationSum3(k,n));
    }
}

class Solutiono216 {
    private List<List<Integer>> res;

    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new LinkedList<>();
        if (k > 9 || n > 45 || n <= 0) {
            return res;
        }

        List<Integer> tmp = new LinkedList<>();
        backtrace(k, n, 0, 1, tmp);
        return res;

    }

    private void backtrace(int k, int target, int sum, int start, List<Integer> tmp) {
        if (k == 0 && target == sum) {
            res.add(new LinkedList<>(tmp));
            return;
        }


        for (int j = start;j < 10;j ++) {
            tmp.add(j);
            backtrace(k - 1,target, sum + j, j + 1, tmp);
            tmp.remove(tmp.size() - 1);
        }

    }
}

class Solution216V2 {

    private List<List<Integer>> res;

    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new LinkedList<>();
        // 从数学角度先做一定程度上的排除
        if (k > 9 || n > 45 || n <= 0) {
            return res;
        }

        List<Integer> tmp = new LinkedList<>();
        backtrace(k, n, 1, tmp);
        return res;

    }

    // 由k个数组成target的和值，从start开始计数，避免重复
    // k代表当前tmp中还能放入多少个数字
    private void backtrace(int k, int target, int start, List<Integer> tmp) {
        if (target < 0) {
            return;
        }

        if (k == 0 && target != 0) {
            return;
        }

        if (k == 0 && target == 0) {
            res.add(new LinkedList<>(tmp));
            return;
        }

        for (int i = start;i < 10;i ++) {
            tmp.add(i);
            backtrace(k - 1, target - i,  i + 1, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
