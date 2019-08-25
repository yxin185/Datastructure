package indi.yxin.stack;

import indi.yxin.array.MyArray;

public class ArrayStack<E> implements Stack<E> {

    private MyArray<E> array;

    public ArrayStack(int capacity) {
       array = new MyArray<E>(capacity);
    }

    public ArrayStack() {
        array = new MyArray<>();
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
    public void push(E e) {
        array.addLast(e);

    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peak() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ArrayStack : size = %d \n", array.getSize()));
        sb.append("down [");
        for (int i = 0; i < array.getSize(); i++) {
            sb.append(array.get(i));
            if(i != array.getSize() - 1) {
                sb.append(',');
            }
        }
        sb.append("] top");
        return sb.toString();
    }
}
