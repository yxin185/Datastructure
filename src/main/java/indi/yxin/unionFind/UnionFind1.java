package indi.yxin.unionFind;

// 第一版Union-Find 1:Quick Find查询时间复杂度低
public class UnionFind1 implements UF {

    private int[] id;

    public UnionFind1(int size) {
        id = new int[size];
        // 初试条件下，为每一个元素赋一个不一样的集合编号
        for (int i = 0;i < id.length;i++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    /**
     * 查找元素p所对应的集合编号
     * @param p
     * @return   O(1)
     */
    private int find(int p) {
        if (p < 0 || p > id.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        // 索引为p的元素的集合编号就是id[p]
        return id[p];
    }

    /**
     * 查看元素p,和q是不是属于一个集合
     * @param p
     * @param q
     * @return   O(1)
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并元素p,q为同一个集合  -->  O(n)
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {

        int pID = find(p);
        int qID = find(q);
        // 如果两个元素就是同一个集合，那么直接返回
        if (qID == pID)
            return;

        for (int i = 0;i < id.length;i ++) {
            if (id[i] == pID)
                id[i] = qID;
        }
    }
}
