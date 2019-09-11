package indi.yxin.unionFind;

// 第二版UnionFind：一棵由孩子指向父亲的树
public class UnionFind2 implements UF {
    // parent[i]表示第i个元素指向哪一个节点
    private int[] parent;

    public UnionFind2(int size) {
        parent = new int[size];

        for (int i = 0;i < size;i++) {
            parent[i] = i;
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
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        // 说明两个元素本来就在一个集合中
        if (pRoot == qRoot)
            return;

        parent[pRoot] = qRoot;
    }

    /**
     * 找到节点p所对应的根节点，即元素p所对应的集合编号
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
