package indi.yxin.LeetCode;

import java.util.*;

/**
 * @Description TODO
 * @Date 2019/11/12 16:00
 **/
public class IntersectionOfTwoArray350 {
    public static void main(String[] args) {
//        Solution350 s = new Solution350();
//        int[] nums1 = {1,2,2,1};
//        int[] nums2 = {2,2};
//        int[] res = s.intersect(nums1, nums2);
//        for (int i : res) {
//            System.out.print(i + " ");
//        }
        // 针对有序数组
        Solution350V2 s = new Solution350V2();
        int[] nums1 = {};
        int[] nums2 = {};
        int[] res = s.intersect(nums1, nums2);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}

class Solution350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0;i < nums1.length;i ++) {
            if (!map.containsKey(nums1[i])) {
                map.put(nums1[i], 1);
            } else {
                map.put(nums1[i], map.get(nums1[i]) + 1);
            }
        }
        Vector<Integer> v = new Vector<>();
        for (int i = 0;i < nums2.length;i ++) {
            if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
                v.add(nums2[i]);
                map.put(nums2[i],map.get(nums2[i]) - 1);
            }
        }
        int[] res = new int[v.size()];
        int i = 0;
        for (int n : v) {
            res[i ++] = n;
        }
        return res;

    }
}
// 针对有序数组
class Solution350V2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        // 使用两个指针，一个指向nums1，一个指向nums2
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i ++;
                j ++;
            } else if (nums1[i] < nums2[j]) {
                i ++;
            } else {
                j ++;
            }
        }
        int[] res = new int[list.size()];
        for (int k = 0;k < list.size();k ++)  {
            res[k] = list.get(k);
        }

        return res;
    }
}
