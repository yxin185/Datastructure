package indi.yxin.BST;

public class TestBST {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 8};
        for (int num : nums) {
            bst.add(num);
        }
//        bst.postOrder();
//        System.out.println("----");
//        bst.postOrderNR();
//        System.out.println("----");
//        bst.levelOrder();
//        System.out.println("----");
//        System.out.println(bst.minimum());
//        System.out.println(bst.removeMin());
//        bst.removeMin();
//        System.out.println("---");
//        System.out.println(bst);
        System.out.println("--------------");
        bst.add(7);
        System.out.println(bst);

    }
}
