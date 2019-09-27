package indi.yxin.LeetCode;

import java.util.LinkedList;
import java.util.List;

public class Combinations77 {

    public static void main(String[] args) {
        Solution1 s = new Solution1();
        System.out.println(s.combine(20, 10));
    }
}

class Solution1 {
    private List<List<Integer>> res;
    public List<List<Integer>> combine(int n, int k) {
        res = new LinkedList<>();
        if (n <= 0 || k <= 0 || k > n) {
            return res;
        }
        List<Integer> c = new LinkedList<>();
        generateCombinations(n, k, 1, c);

        return res;
    }

    // 求解C（n,k），当前已经找到的组合存储在c中，需要从start开始搜索新的元素
    private void generateCombinations(int n, int k, int start, List<Integer> c) {
        if (c.size() == k) {
            // java 引用传递，这个地方就要c来构造一个新的链表
            res.add(new LinkedList<>(c));
            return;
        }
        // 剪枝操作
        // 不剪枝之前  i <= n
        // 还有k - c.size()个空位，所以需要[i...n]中至少有 k - c.size()个元素
        // 即 n - i + 1 >= k - c.size()
        for (int i = start;i <= n - (k - c.size()) + 1;i ++) {
            c.add(i);
            generateCombinations(n, k, i + 1, c);
            c.remove(c.size() - 1);
        }

    }
}


