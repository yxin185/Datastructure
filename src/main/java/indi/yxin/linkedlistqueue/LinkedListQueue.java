package indi.yxin.linkedlistqueue;

import indi.yxin.arrayqueue.Queue;
import indi.yxin.linkedlist.LinkedList;

public class LinkedListQueue<E> implements Queue<E> {

    private class Node {
        public E e;
        public Node next;

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

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head, tail;
    private int size;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {

        if (tail == null) {
            // 此时队列也空
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++;
    }

    @Override
    public E dequeue() {
        // 尾结点插入为O(1),删除为O(n)
        // 所以尾结点为队列头，头结点为队列尾
        // 从tail端插入元素，从head端删除元素
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from a empty queue.");
        }
        Node delNode = head;
        head = head.next;
        delNode.next = null;
        if (head == null) {
            tail = null;
        }
        size --;
        return delNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Empty queue.");
        }
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedListQueue : front ");

        Node curr = head;
        while (curr != null) {
            sb.append(curr.e + "->");
            curr = curr.next;
        }
        sb.append("NULL tail");
        return sb.toString();
    }
}
