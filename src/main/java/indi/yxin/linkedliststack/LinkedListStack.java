package indi.yxin.linkedliststack;

import indi.yxin.linkedlist.LinkedList;
import indi.yxin.stack.Stack;

public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public LinkedListStack() {
        list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        // 因为往链表头添加是O(1)级别的
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.deleteFirst();
    }

    @Override
    public E peak() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: top ");
        sb.append(list);
        return sb.toString();
    }
}
