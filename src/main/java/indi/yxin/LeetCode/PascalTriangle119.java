package indi.yxin.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle119 {

    public static void main(String[] args) {
        int rowIndex = 10;
        Solution119V1 s = new Solution119V1();
        System.out.println(s.generate(rowIndex));
        V2 v = new V2();
        System.out.println();
        List<Integer> res = v.generate(rowIndex);
        System.out.println(res);
    }
}

class Solution119V1 {
    public List<Integer> generate(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        List<Integer> prev;
        if (rowIndex < 0)
            return res;

        // 初始化情况下第0行只有一个1
        res.add(1);

        for (int i = 1;i <= rowIndex;i ++) {
            prev = new ArrayList<>(res);
            for (int j = 1;j < i;j ++) {
                res.set(j, prev.get(j - 1) + prev.get(j));
            }
            res.add(1);
        }

        return res;
    }
}

class V2 {
    public List<Integer> generate(int rowIndex) {

        List<Integer> res = new ArrayList<>();
        if (rowIndex < 0) {
            return res;
        }

        for (int i = 0;i <= rowIndex;i ++) {
            res.add(1);
            for (int j = i - 1;j > 0; j --) {
                res.set(j, res.get(j) + res.get(j - 1));
            }
        }
        return res;
    }
}
