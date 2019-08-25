package indi.yxin.arrayqueue;

import indi.yxin.array.MyArray;

public class ArrayQueue<E> implements Queue<E> {

    private MyArray<E> array;

    public ArrayQueue() {
        array = new MyArray<>();
    }

    public ArrayQueue(int capacity) {
        array = new MyArray<>(capacity);
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFisrt();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ArrayQueue : size = %d , capacity = %d \n", array.getSize(), array.getCapacity()));
        sb.append("head [");
        for (int i=0;i < array.getSize();i++) {
            sb.append(array.get(i));
            if (i != array.getSize() - 1) {
                sb.append(',');
            }
        }
        sb.append("] tail");
        return sb.toString();
    }
}
