//package indi.yxin.LeetCode;
//
//import java.util.LinkedList;
//import java.util.List;
//
//public class NQueens51 {
//
//    public static void main(String[] args) {
//        Solution51 s = new Solution51();
//        int n = 4;
//        List<List<String>> res = s.solveNQueens(n);
//        System.out.println(res);
//
//        for (int j = 0;j < res.size();j ++) {
//            if (j % n == 0)
//                System.out.println();
//            System.out.println(res.get(j).get(0));
//        }
//    }
//}
//
//class Solution51 {
//    // 存放结果
//    private List<List<String>> res;
//    // col:标识该列是否可用
//    // dia1:标志从右上至左下的对角线是否有元素，即是否可用
//    // dia2:标志从左上至右下的对角线是否有元素
//    // boolean 默认都初始化为false
//    private boolean[] col,dia1,dia2;
//
//    public List<List<String>> solveNQueens(int n) {
//        // 初始化 res,col,dia1,dia2
//        res = new LinkedList<>();
//        col = new boolean[n];
//        // 对角线的个数为 2 * n - 1
//        dia1 = new boolean[2 * n - 1];
//        dia2 = new boolean[2 * n - 1];
//        // 存放第index行的皇后的位置
//        List<Integer> row = new LinkedList<>();
//        putQueen(n, 0, row);
//
////        System.out.println(res);
//        return res;
//    }
//
//    // 尝试在一个 N 皇后问题中，摆放第index行的皇后的位置
//    private void putQueen(int n, int index, List<Integer> row) {
//
//        if (index == n) {
//            // 根据存放的皇后位置，生成一个合法的棋盘
//            for (int i = 0;i < n;i ++) {
//                // 每一次生成一行棋盘
//                res.add(generateBoard(n, row.get(i)));
//            }
//            return;
//        }
//        // 有了index行，下面开始遍历每一列
//        // 看看哪些列还可以摆放皇后,i代表列值
//        for (int i = 0;i < n;i ++) {
//            // 如果满足下述条件，则该位置可以摆放皇后
//            if (!col[i] && !dia1[index + i] && !dia2[index - i + n - 1]) {
//                // 满足上述情况，说明这个位置可以放置一个皇后
//                row.add(i);
//                col[i] = true;
//                dia1[index + i] = true;
//                dia2[index - i + n - 1] = true;
//                putQueen(n, index + 1, row);
//                // 回溯过程
//                col[i] = false;
//                dia1[index + i] = false;
//                dia2[index - i + n - 1] = false;
//                row.remove(row.size() - 1);
//            }
//        }
//
//    }
//
//    private List<String> generateBoard(int n, int rowN) {
//        List<String> board = new LinkedList<>();
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0;i < n;i ++) {
//            if (i != rowN)
//                sb.append(".");
//            else
//                sb.append("Q");
//        }
//        board.add(sb.toString());
//
////        System.out.println("board:"+board);
//        return board;
//    }
//}
package indi.yxin.LeetCode;

import java.util.LinkedList;
import java.util.List;

public class NQueens51 {

    public static void main(String[] args) {
        Solution51 s = new Solution51();
        int n = 4;
        List<List<String>> res = s.solveNQueens(n);
        System.out.println(res);
    }
}

class Solution51 {
    // 存放结果
    private List<List<String>> res;
    // col:标识该列是否可用
    // dia1:标志从右上至左下的对角线是否有元素，即是否可用
    // dia2:标志从左上至右下的对角线是否有元素
    // boolean 默认都初始化为false
    private boolean[] col,dia1,dia2;

    public List<List<String>> solveNQueens(int n) {
        if (n == 0) {
            // 如果n为0,则返回 [[]]
            List<String> s = new LinkedList<>();
            List<List<String>> res = new LinkedList<>();
            res.add(s);
            return res;
        }
        // 初始化 res,col,dia1,dia2
        res = new LinkedList<>();
        col = new boolean[n];
        // 对角线的个数为 2 * n - 1
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        // 存放第index行的皇后的位置
        List<Integer> row = new LinkedList<>();
        // 开始递归函数
        putQueen(n, 0, row);

//        System.out.println(res);
        return res;
    }

    // 尝试在一个 N 皇后问题中，摆放第index行的皇后的位置，结果记录在row中
    private void putQueen(int n, int index, List<Integer> row) {

        if (index == n) {
            res.add(generateBoard(n, row));
            return;
        }
        // 有了index行，下面开始遍历每一列
        // 看看哪些列还可以摆放皇后,i代表列值
        for (int i = 0;i < n;i ++) {
            // 如果满足下述条件，则该位置可以摆放皇后
            if (!col[i] && !dia1[index + i] && !dia2[index - i + n - 1]) {
                // 满足上述情况，说明这个位置可以放置一个皇后
                row.add(i);
                col[i] = true;
                dia1[index + i] = true;
                dia2[index - i + n - 1] = true;
                putQueen(n, index + 1, row);
                // 回溯过程
                col[i] = false;
                dia1[index + i] = false;
                dia2[index - i + n - 1] = false;
                row.remove(row.size() - 1);
            }
        }

    }

    private List<String> generateBoard(int n, List<Integer> row) {
        List<String> board = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < n;i ++) {
            sb.append(".");
        }
        for (int i = 0;i < n;i ++) {
            sb.setCharAt(row.get(i),'Q');
            board.add(sb.toString());
            sb.setCharAt(row.get(i),'.');
        }
        return board;
    }
}
