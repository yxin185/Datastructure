package indi.yxin.unionFind;

/**
 * 基于rank-树的高度，进行优化
 * rank[i]代表节点i所在的根节点的高度
 *
 * 第5版：路径压缩  path compressing
 */
public class UnionFind5 implements UF {

    private int[] parent;
    private int[] rank;

    public UnionFind5(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0;i < size;i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;

        if (rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if (rank[pRoot] > rank[qRoot])
            parent[qRoot] = pRoot;
        else {  //rank[pRoot] == rank[qRoot]，这时候要考虑高度需要增加1
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }

    /**
     * 路径压缩主要操作在find这个函数中，在查找节点根节点的过程中顺便压缩树的高度
     * @param index
     * @return
     */
    private int find(int index) {
        if (index < 0 || index > parent.length) {
            throw new IllegalArgumentException("index is illegal.");
        }
        while (index != parent[index]){
            // 把index的父亲节点更新为他父亲节点的父亲节点
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }
}
