package indi.yxin.LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GenerateParentheses22 {
    public static void main(String[] args) {
        Solution22 s = new Solution22();
        int n = 2;
        System.out.println(s.generateParenthesis(n));
    }
}
// 左括号等于右括号了，就只能放左括号
// 左括号要是大于右括号，那么既可以放左括号，也可以放右括号
class Solution22 {
    private List<String> res;
    // 添加括号的时候，左括号的数量大于等于右括号的数量
    public List<String> generateParenthesis(int n) {
        res = new LinkedList<>();
        if (n <= 0) {
            return res;
        }
        // 只要 n > 0，则第一个括号一定是左括号

        backtrace(n,0, 0, "");
        return res;
    }

    /**
     * @param n
     * @param left:左括号数量
     * @param right:右括号数量
     */
    private void backtrace(int n, int left, int right, String s) {
        if (s.length() == 2 * n) {
            res.add(s);
            return;
        }

        if (left < n) {
            backtrace(n, left + 1, right, s + "(");
        }
        if (right < left) {
            backtrace(n, left, right + 1, s + ")");
        }
    }
}


