package indi.yxin.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class PascalTraiangle118 {
    public static void main(String[] args) {
        int numRows = 5;
        List<List<Integer>> res = new ArrayList<>();
        Solution118 s = new Solution118();
        res = s.generate(numRows);
        System.out.println(res);
    }
}

class Solution118 {
    public List<List<Integer>> generate(int numRows) {
        // 因为程序中主要涉及到链表元素的查找
        // 所以使用 ArrayList 来实例化
        List<List<Integer>> triangle = new ArrayList<>();
        // 1：要是行数为0，直接返回空的列表就行
        if (numRows == 0)
            return triangle;
        // 2：初始化的情况下，用第一行去推后面的第N行
        // 第一行的唯一一个元素就是 1
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);

        // 3：对于第1~n行，第i行只有i个元素
        for (int i = 1;i < numRows;i ++) {
            List<Integer> row = new ArrayList<>();
            // 4：因为后一行需要前一行的信息，所以记录一下前一行
            // 因为前一行已经存入 triangle 中，所以直接去就可以
            List<Integer> prevRow = triangle.get(i - 1);
            // row 的第一个元素一定是1
            row.add(1);
            for (int j = 1;j < i;j ++) {
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            // row 的最后一个元素也一定是1
            row.add(1);

            triangle.add(row);
        }
        return triangle;

    }
}
