package indi.yxin.LeetCode;

public class NumberOfIslands200 {
    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'},
        };
        Solution200 s = new Solution200();
        System.out.println(s.numIslands(grid));
    }
}

class Solution200 {
    // 行
    private int m;
    // 列
    private int n;
    // 二维空间记录上下左右的方向
    private int[][] dire = {{-1,0},{0,1},{1,0},{0,-1}};
    // 记录这一片区域是否被访问过了
    private boolean[][] visited;

    public int numIslands(char[][] grid) {
        m = grid.length;
        if (m == 0)
            return 0;
        n = grid[0].length;

        visited = new boolean[m][n];
        for (int i = 0;i < m;i ++) {
            for (int j = 0;j < n;j ++) {
                visited[i][j] = false;
            }
        }
        int res = 0;
        for (int i = 0;i < m;i ++) {
            for (int j = 0;j < n;j ++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    res ++;
                    // 然后递归将与这一块陆地相连接的所有陆地进行标记
                    dfsFloodFill(grid, i, j);
                }
            }
        }
        return res;


    }

    // 从grid[x][y]的位置开始，进行floodfill
    // 保证(startx,starty)合法，且grid[x][y]是没有被访问过的陆地
    private void dfsFloodFill(char[][] grid, int startx, int starty) {
        visited[startx][starty] = true;
        // 开始向四个方向进行递归
        for (int i = 0;i < 4;i ++) {
            int newx = startx + dire[i][0];
            int newy = starty + dire[i][1];

            if (inArea(newx,newy) && !visited[newx][newy] && grid[newx][newy] == '1')
                dfsFloodFill(grid, newx, newy);
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m
                && y >= 0 && y < n;
    }
}
