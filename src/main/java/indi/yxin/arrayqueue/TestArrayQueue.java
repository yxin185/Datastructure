package indi.yxin.arrayqueue;

public class TestArrayQueue {
    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        for (int i = 0;i < arrayQueue.getCapacity();i++) {
            arrayQueue.enqueue(i);
        }
        for (int i = 0;i < 8;i++) {
            arrayQueue.dequeue();
            System.out.println(arrayQueue);
        }
    }
}
