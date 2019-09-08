package indi.yxin.segmentTree;

public class SegmentTree<E> {
    private E[] tree;//存树节点
    private E[] data;//存数据
    private Merger<E> merger;//融合器，将两个元素按业务需求进行融合


    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0;i < arr.length;i++) {
            data[i] = arr[i];
        }
        // 根据分析，线段树的空间大小约为数据大小的4倍，即4*n
        tree = (E[]) new Object[4 * arr.length];
        /**
         * 建一棵线段树，需要的参数为根节点，左端坐标，以及右端坐标为整个数据长度-1
         */
        buildSegmentTree(0, 0, arr.length - 1);
    }

    /**
     * 在treeindex的位置创建表示区间[l...r]的线段树
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // 递归算法，终止条件：当左端点=右端点时，说明该线段树只有一个节点了
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        // 使用二分搜索树中的一样的方法。记录当前节点，左右孩子节点的位置
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        // 记录区间中的的位置
        // 为了避免整形溢出，使用这个公式
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        // 将treeIndex的左右两个孩子节点融合成一个，按照用户的自定义需求进行
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    private int rightChild(int treeIndex) {
        return 2 * treeIndex + 2;
    }

    private int leftChild(int treeIndex) {
        return 2 * treeIndex + 1;
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Illegal index.");
        }
        return data[index];
    }

    /**
     * 返回区间[queryL, queryR]的值
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int queryL,int queryR) {
        if (queryL < 0 || queryL >= data.length - 1 ||
            queryR < 0 || queryR >= data.length - 1 || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal.");
        }

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在以treeIndex为根的线段树中[l...r]范围内，搜索区间[queryL...queryR]的值
     * @param treeIndex
     * @param l
     * @param r
     * @param queryL
     * @param queryR
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        // 递归终止条件
        if (queryL == l && queryR == r)
            return tree[treeIndex];

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // 查询的左边界在中间节点的右边，说明查询值只在该节点的右子树,所以进入右子树进行查询
        if (queryL >= mid + 1)
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        else if (queryR <= mid)
            return query(leftTreeIndex, l, mid, queryL, queryR);

        // 说明这个时候查询区域至少跨两个节点,查询区域也被划分开了
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        // 将两个值按照需求融合
        return merger.merge(leftResult, rightResult);
    }

    /**
     * 将index处的元素更改为e
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");

        set(0, 0, data.length - 1, index, e);
    }

    // 在以treeIndex为根的线段树中更新index处的元素为e
    private void set(int treeIndex, int l, int r, int index, E e) {
        // 递归终止条件
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index >= mid + 1)
            set(rightTreeIndex, mid + 1, r, index, e);
        else // index <= mid
            set(leftTreeIndex, l, mid, index, e);

        // 因为Index处的元素发生了改变，会对他的父辈产生影响，所以最后，treeIndex需要使用一下merge将两边的元素融合起来
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0;i < tree.length;i++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else
                res.append("null");

            if (i != tree.length - 1)
                res.append(',');
        }
        res.append(']');
        return res.toString();
    }

}
