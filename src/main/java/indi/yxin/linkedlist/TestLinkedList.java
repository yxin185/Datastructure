package indi.yxin.linkedlist;

public class TestLinkedList {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0;i < 10;i++) {
            list.addLast(i);
            System.out.println(list);
        }
        list.add(2, 666);
        System.out.println(list);

        list.delete(0);
        System.out.println(list);
    }
}
