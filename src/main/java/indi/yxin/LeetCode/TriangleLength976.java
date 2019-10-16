package indi.yxin.LeetCode;

import java.util.Arrays;

public class TriangleLength976 {
    public static void main(String[] args) {
        V2Solution976 s = new V2Solution976();
        int[] arr = {2,1,2};
        System.out.println(s.largestPerimeter(arr));
    }

}

class Solution976 {
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        int max = 0;
        int perimeter;
        for (int i = 0;i < A.length - 2;i ++) {
            if (isTriangle(A[i], A[i + 1], A[i + 2])) {
                perimeter = A[i] + A[i + 1] + A[i + 2];
                if (perimeter > max)
                    max = perimeter;
            }
        }
        return max;
    }

    public boolean isTriangle(int a, int b, int c) {
        if (a + b <= c || a + c <= b || b + c <= a) {
            return false;
        }
        return true;
    }
}
// 对于一个已经排好序的数组，从后往前遍历的效率会更高
// 要是后面的数都能构成三角形，肯定比前面数构成的三角形的周长更大
class V2Solution976 {
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 3;i >= 0;i --) {
            if (A[i] + A[i + 1] > A[i + 2])
                return A[i] + A[i + 1] + A[i + 2];
        }
        return 0;
    }
}