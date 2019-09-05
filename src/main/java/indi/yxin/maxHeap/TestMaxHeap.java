package indi.yxin.maxHeap;

import java.util.Random;

public class TestMaxHeap {

    private static double testHeap(Integer[] testData,boolean isHeapify) {
        long startTime = System.nanoTime();
        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(testData);
        } else {
            maxHeap = new MaxHeap<>();
            for (int num : testData) {
                maxHeap.add(num);
            }
        }
        // 对堆是否建成进行测试
        int[] arr = new int[testData.length];
        // 对这个数组进行降序排列
        for (int i = 0;i < testData.length;i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1;i < testData.length;i++){
            if (arr[i-1] < arr[i]) {
                throw new IllegalArgumentException("Error");
            }
        }

        System.out.println("MaxHeap is completed.");

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int n = 1000000;
//        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        Integer[] testData = new Integer[n];
        for (int i = 0;i < n;i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }
        double time1 = testHeap(testData, false);
        System.out.println("不使用heapify的时间=" + time1);
        double time2 = testHeap(testData, true);
        System.out.println("使用heapify的时间=" + time2);
    }
}
