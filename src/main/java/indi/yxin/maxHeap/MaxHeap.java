package indi.yxin.maxHeap;

import indi.yxin.array.MyArray;


// index从0开始
public class MaxHeap<E extends Comparable<E>> {

    private MyArray<E> data;
    public MaxHeap(int capacity) {
        data = new MyArray<>(capacity);
    }
    public MaxHeap() {
        data = new MyArray<>();
    }
    // 将给定的一个数组转换成一个最大堆，使用的是heapify方法

    public MaxHeap(E[] arr) {

        data = new MyArray<>(arr);
        // 实现heapify过程
        // 从索引为最后一个非叶子节点开始，依次执行下沉siftDown操作
        // 对索引为0的节点也要执行下沉操作
        for (int i = parent(arr.length - 1);i >= 0;i--) {
            siftDown(i);
        }
    }

    // 返回堆中元素个数
    public int size() {
        return data.getSize();
    }
    // 返回堆是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }
    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index){
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }
    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int rightChild(int index) {
        return index * 2 + 2 ;
    }

    // 向堆中添加元素,O(log n)
    public void add(E e) {
        // 先把这个值加入到数组末尾
        data.addLast(e);
        // 对这个数据进行调整，使得其满足最大堆的性质
        // 对最后一个元素进行调整，最后一个元素的索引就是data.getSize() - 1
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {
        // 比较这个索引K对应的节点与其父节点的数据大小
        // 如果父亲节点的值小于该节点，则进行位置交换
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(parent(k),k);
            k = parent(k);
        }
    }

    // 看堆中最大元素
    public E findMax() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Heap is empty.");
        return data.get(0);
    }

    // 取出堆中最大元素,O(log n)
    public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        // 上述过程破坏了最大堆的性质，所以需要重新调整结构
        siftDown(0);
        return ret;
    }

    private void siftDown(int k) {
        // 说明这个索引还有左孩子节点
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            // 存在右孩子节点并且右孩子节点比当前节点大
            if(j + 1 < data.getSize() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = rightChild(k);
            }
            // data[j]是leftChild和rightChild中的最大值
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;

            data.swap(k, j);
            k = j;
        }
    }

    // 取出堆中最大的元素，并替换成e,一次O(log n)
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("MaxHeap [");
        for (int i=0;i < data.getSize();i++) {
            if (i != data.getSize() - 1)
                res.append(data.get(i) + ",");
            else
                res.append(data.get(i) + "]");
        }
        return res.toString();
    }
}
