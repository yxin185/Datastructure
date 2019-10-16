package indi.yxin.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class PermutationKth60 {

    public static void main(String[] args) {
        Solution60 s = new Solution60();
        Solution60V2 v2 = new Solution60V2();
        int n = 9;
        int k = 332232;
        long start1 = System.currentTimeMillis();
        System.out.println(s.getPermutation(n,k));
        long end1 = System.currentTimeMillis();
        long start2 = System.currentTimeMillis();
        System.out.println(v2.getPermutation(n,k));
        long end2 = System.currentTimeMillis();
        System.out.println("优化前：" + (end1 - start1));
        System.out.println("优化后：" + (end2 - start2));

    }
}
/*
此种解法超出时间限制,做出修改，当res中的值的数量达到 要求的 k时，就跳出循环，返回这个数
 */
class Solution60 {
    private List<String> res;
    private boolean[] used;
    private int count;
    public String getPermutation(int n, int k) {
        res = new ArrayList<>();
        used = new boolean[n];
        count = 0;
        if (n < 1 || n > 9) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        generatePermutation(n, k, used, s);
        return res.get(k - 1);
    }

    private void generatePermutation(int n, int k, boolean[] used, StringBuilder tmp) {
        if (tmp.length() == n) {
            res.add(tmp.toString());
            count ++;
            return;
        }

        for (int i = 0;i < n; i ++) {
            if (!used[i]) {
                used[i] = true;
                tmp.append(i + 1);
                generatePermutation(n, k, used, tmp);
                if (count == k)
                    break;
                used[i] = false;
                tmp.deleteCharAt(tmp.length() - 1);
            }
        }
    }
}
// 由于上面还是运行结果不佳
// 考虑使用数学性质进行优化\
// 这种做法的优势体现在当 取的位置靠后时

/**
 * 对于 n 而言，[1,2,3,,,,n]
 * 则以每一个数字开头的排列数为 A(n-1,n-1)种
 * 如果n = 5,则以1开头的排列共有 A(4,4) = 24种
 * 因为结果集下标从0开始
 * 所以以1,2,3,4,5开头的排列的下标为
 * 1:0-23
 * 2:24-47
 * 3:48-71
 * 4:72-95
 * 5:96-119
 * 则 第 k 个排列应该处在第 k / A(n-1,n-1) 个数字开始的排列中，我们只去遍历以这个数字开始的排列即可
 * 所以 第 96 个数应该在 (96 - 1) / 24 = 3(即数字4的那个排列中)
 */
class Solution60V2 {
    private List<String> res;
    private boolean[] used;
    private int count;
    public String getPermutation(int n, int k) {
        res = new ArrayList<>();
        used = new boolean[n];
        count = 0;
        if (n < 1 || n > 9) {
            return "";
        }
        // 获得 n - 1 的阶乘
        int helper = factorial(n - 1);
        // 获得排列的第一个元素是多少
        int kth = (k - 1) / helper + 1;
        StringBuilder tmp = new StringBuilder();
        // 记录返回的是以 kth 这个数字开头的排列中的第几个数
        int num = k - helper * (kth - 1);
        backtrace(n, tmp.append(kth), used, num, kth);
        // k - helper * (kth - 1) 指的是要去获取 以 kth 这个数字开头的排列中的该位置的元素
        return res.get(num - 1);
    }

    private void backtrace(int n, StringBuilder tmp, boolean[] used, int num, int kth) {
        if (tmp.length() == n) {
            res.add(tmp.toString());
            count ++;
            return;
        }

        for (int i = 0; i < n;i ++) {
            if (!used[i]) {
                if ((i + 1) == kth) {
                    continue;
                }
                tmp.append(i + 1);
                used[i] = true;
                backtrace(n, tmp, used, num, kth);
                if (count == num) {
                    break;
                }
                tmp.deleteCharAt(tmp.length() - 1);
                used[i] = false;
            }

        }
    }
    // 如果这个地方使用递归，会导致 leetCode 栈溢出
    private int factorial(int i) {
        int res = 1;
        for (int j = 1;j <= i;j ++) {
            res *= j;
        }
        return res;
    }
}
