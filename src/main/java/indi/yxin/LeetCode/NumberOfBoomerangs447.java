package indi.yxin.LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2019/11/12 20:27
 **/
public class NumberOfBoomerangs447 {
    public static void main(String[] args) {
        Solution447 s = new Solution447();
        int[][] arr = {{0,0},{1,0},{2,0}};
        int res = s.numberOfBoomerangs(arr);
        System.out.println(res);
    }
}

class Solution447 {
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        // 时间复杂度 O(n^2)
        // 空间复杂度 O(n)
        // i,j代表第几个点
        for (int i = 0;i < points.length;i ++) {
            // KEY:与第i个点的距离
            // VALUE:有多少个点与i的距离为KEY
            // 因为要考虑顺序，所以每一个点都有
            Map<Integer,Integer> map = new HashMap<>();
            for (int j = 0;j < points.length;j ++) {
                if (j != i) {
                    // 将两个点的距离作为键存储
                    if ( !map.containsKey( dis(points[i], points[j]) ) ) {
                        map.put( dis(points[i], points[j]), 1);
                    } else {
                        map.put( dis(points[i], points[j]), map.get(dis(points[i], points[j])) + 1);
                    }
                }
            }

            // 遍历一遍 map, 每一个键对应的值就是与第i个点的距离为该键值的点的个数
            for ( Integer key : map.keySet() ) {
                res += map.get(key) * ( map.get(key) - 1 );
            }
        }
        return res;
    }
    // 根据点坐标的取值范围可以确定不会导致整型越界
    private int dis(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) +
                (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}
