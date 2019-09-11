package indi.yxin.unionFind;

// 第三版UnionFind：根据每一个根节点所包含的节点数来优化这棵树

/**
 * 根据节点包含元素个数来优化树的  合并操作
 */
public class UnionFind3 implements UF {
    // parent[i]表示第i个元素指向哪一个节点
    private int[] parent;
    private int[] sz;

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0;i < size;i++) {
            parent[i] = i;
            sz[i] = 1; // 每一个节点的初始节点数都为1
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // O(h)
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
    // O(h)
    // 根据两个元素所在树的元素个数不同判断合并方向
    // 元素个数少的去连接元素个数多的
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        // 说明两个元素本来就在一个集合中
        if (pRoot == qRoot)
            return;
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }

    /**
     * 找到节点p所对应的根节点，即元素p所对应的集合编号，
     * 以此减少整棵树的高度
     * O(h)复杂度，h为树高
     * @param p
     * @return
     */
    private int find(int p) {
        if (p < 0 || p > parent.length) {
            throw new IllegalArgumentException("p is illegal.");
        }

        while (p != parent[p])
            p = parent[p];

        return p;
    }
}
