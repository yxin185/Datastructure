package indi.yxin.segmentTree;

public class TestSegmentTree {
    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
//        SegmentTree<Integer> segTree = new SegmentTree<>(nums, new Merger<Integer>() {
//            @Override
//            public Integer merge(Integer a, Integer b) {
//                // 可以按照需要实现功能
//                return a + b;
//            }
//        });
        // 使用lambda 表达式
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, (a,b) -> a + b);
        System.out.println(segTree);

        System.out.println(segTree.query(0,5));
    }
}
