package indi.yxin.LeetCode;

import java.util.HashSet;

public class FriendCircles547 {
    public static void main(String[] args) {
        Solution547 s = new Solution547();
        int[][] M = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
        int ret = s.findCircleNum(M);
        System.out.println(ret);
    }
}

class Solution547 {
    private int[] id; // 朋友圈编号
    private int[] sz; // 每一个朋友圈的人数
    
    public int findCircleNum(int[][] M) {
        id = new int[M.length];
        sz = new int[M.length];
        
        for (int i = 0;i < M.length;i ++) {
            id[i] = i;
            sz[i] = 1;
        }
        // 设置朋友圈,对 id[] 进行划分
        setCircle(M);
        // 计算朋友圈数量
        // 遍历一遍 id,相同值被 set 去除(这里有问题)
        // 这里有一点问题就是,因为 id 存的不一定是根节点，所以光看不同 id 的数量
        // 无法准确判断朋友圈数量
        // 应该看有多少个不同的根节点
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0;i < id.length;i ++) {
            set.add(find(i));
        }
        return set.size();
    }
    // 因为朋友圈数组是一个对称数组，只用考虑主对角线上方的元素就行
    private void setCircle(int[][] M) {
        for (int i = 0;i < M.length;i ++) {
            for (int j = i + 1;j < M[i].length;j ++) {
                // 说明有朋友关系
                if (M[i][j] == 1) {
                    unionFriends(i, j);
                }
            }
        }
    }

    private void unionFriends(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else if (sz[pRoot] > sz[qRoot]) {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            id[qRoot] = pRoot;
            sz[pRoot] += 1;
        }
    }

    private int find(int p) {
        // TODO: if p is legal
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }
}
