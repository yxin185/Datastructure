package indi.yxin.LeetCode;

import java.util.LinkedList;
import java.util.List;

// 有问题
public class FindSubString438 {
    public static void main(String[] args) {
        Solution438 s1 = new Solution438();
        String s = "cbaebabacd";
        String p = "abc";
        LinkedList<Integer> res= (LinkedList<Integer>) s1.findAnagrams(s, p);
        System.out.println(res);
    }
}

class Solution438 {
    public List<Integer> findAnagrams(String s, String p) {
        // 滑动窗口解决,滑动窗口的最大值就是3
        int[] freq = new int[26];   // 存放26个字母是否出现
        int ns = s.length();
        int np = p.length();
        boolean[] mark = new boolean[26];
        List<Integer> res = new LinkedList<>();
        if (np > ns)
            return res;
        for (int i = 0;i < np;i ++) {
            freq[p.charAt(i) - 'a'] ++;
        }

        int l = 0;
        while (l <= ns - np) {
            
            l ++;
        }
        return res;
    }

}