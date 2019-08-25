package indi.yxin.loopqueue;

import indi.yxin.arrayqueue.ArrayQueue;

public class TestLoopQueue {
    public static void main(String[] args) {
//        LoopQueue<Integer> loopQueue = new LoopQueue<>();
//        for (int i = 0;i < loopQueue.getCapacity();i++) {
//            loopQueue.enqueue(i);
//            System.out.println(loopQueue);
//        }
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();

        long start1 = System.currentTimeMillis();
        for (int i = 0;i < 100000;i++) {
            loopQueue.enqueue(i);
        }
        for (int i= 0;i < loopQueue.getSize();i++) {
            loopQueue.dequeue();
        }
        long end1 = System.currentTimeMillis();
        System.out.println("loop : " + (end1 - start1) + " ms");

        // ArrayQueue出栈花费大量时间
        long start2 = System.currentTimeMillis();
        for (int i = 0;i < 100000;i++) {
            arrayQueue.enqueue(i);
        }
        for (int i = 0;i < arrayQueue.getSize();i++) {
            arrayQueue.dequeue();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("array : " + (end2 - start2) + " ms");
    }
}
