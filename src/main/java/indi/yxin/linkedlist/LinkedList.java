package indi.yxin.linkedlist;

public class LinkedList<E> {

    private class Node {
        // Node 成员变量
        public E e;
        public Node next;

        // Node构造函数
        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }
    }

//    private Node head;
    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    // 获取链表长度
    public int getSize() {
        return size;
    }
    // 判断链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 在链表中间index处添加元素e
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Index is illegal.");
        }
        Node prev = dummyHead;
        for (int i = 0;i < index;i++) {
            prev = prev.next;
        }
//        Node newNode = new Node(e);
//        newNode.next = prev.next;
//        prev.next = newNode;
        // 上面三行等同于下面一行
          prev.next = new Node(e, prev.next);

        size ++;
    }

    // 在链表头添加元素e
    public void addFirst(E e) {
        add(0, e);
    }
    // 在链表末尾添加元素
    public void addLast(E e) {
        add(size, e);
    }

    // 删除索引为index的元素，并返回该元素
    public E delete(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Delete failed. Index is illegal.");
        }
        Node prev = dummyHead;
        for (int i = 0;i < index;i++) {
            prev = prev.next;
        }
        // 待删除节点为prev节点的下一个节点
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;

        size --;
        return delNode.e;
    }

    public E deleteFirst() {
        return delete(0);
    }

    public E deleteLast() {
        return delete(size - 1);
    }

    // 获得链表的第index个元素并返回
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        Node curr = dummyHead.next;
        for (int i = 0;i < index;i++) {
            curr = curr.next;
        }
        return curr.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    // 修改链表的第index个元素为e
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }
        Node curr = dummyHead.next;
        for (int i = 0;i < index;i++) {
            curr = curr.next;
        }
        curr.e = e;
    }

    // 查找链表中是否有元素e，如果有返回index，否则返回-1
    public boolean contains(E e) {
        Node curr = dummyHead.next;
        while (curr != null) {
            if(curr.e.equals(e)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node curr = dummyHead.next;
        while (curr != null) {
            sb.append(curr.e + "->");
            curr = curr.next;
        }
        sb.append("NULL");
        return sb.toString();
    }
}
