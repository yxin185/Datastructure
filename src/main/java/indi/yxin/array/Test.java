package indi.yxin.array;

public class Test {

    public static void main(String[] args) {
        MyArray<Integer> arr = new MyArray<>(10);
        for (int i=0;i < 3;i++) {
            arr.addLast(i);
            System.out.println(arr);
        }
        arr.removeLast();
        System.out.println(arr);

    }
}
