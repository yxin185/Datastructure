package indi.yxin.linkedlistqueue;

public class Test {
    public static void main(String[] args) {
        LinkedListQueue<Integer> listQueue = new LinkedListQueue<>();
        for (int i = 0;i < 10;i++) {
            listQueue.enqueue(i);
            System.out.println(listQueue);
        }

        listQueue.dequeue();
        System.out.println(listQueue);
    }
}
