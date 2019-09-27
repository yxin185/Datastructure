package indi.yxin.LeetCode;

public class WordSearch79 {
    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        Solution79 s = new Solution79();
        System.out.println(s.exist(board,""));

    }
}

class Solution79 {
    // 记录行数
    private int m;
    // 记录列数
    private int n;
    // 记录四个方向，二维平面中设计的偏移量，常用
    private int[][] dire = {{-1,0},{0,1},{1,0},{0,-1}};
    // 记录这一个格子是否被访问过
    private boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        m = board.length;
        assert m > 0;
        n = board[0].length;
        // 如果字符串为空，该怎么处理呢
        if (word.length() == 0)
            return false;

        // 将visited初始化
        visited = new boolean[m][n];
        for (int i = 0;i < m;i ++) {
            for (int j = 0;j < n;j ++) {
                // 没有访问过就是false
                // 访问过的就是true
                visited[i][j] = false;
            }
        }

        for (int i = 0;i < m;i ++) {
            for (int j = 0;j < n;j ++) {
                if (searchWord(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean searchWord(char[][] board, String word, int index, int startx, int starty) {
        // 递归终止条件
        if (index == word.length() - 1) {
            return board[startx][starty] == word.charAt(index);
        }

        if (board[startx][starty] == word.charAt(index)) {
            visited[startx][starty] = true;
            // 从 startx, starty出发，向四个方向寻找，i代表第i个方向
            for (int i = 0;i < 4;i ++) {
                int newX = startx + dire[i][0];
                int newY = starty + dire[i][1];
                if (inArea(newX, newY) && !visited[newX][newY]) {
                    if (searchWord(board, word, index + 1, newX, newY)) {
                        // 上面为真，说明找到这一个满足条件的了
                        return true;
                    }
                }
            }
            // 递归回溯过程
            visited[startx][starty] = false;
        }

        return false;
    }

    private boolean inArea(int newX, int newY) {
        return newX >= 0 && newX < m
                && newY >= 0 && newY < n;
    }
}
