package indi.yxin.LeetCode;

public class LongestSubString3 {
    public static void main(String[] args) {
        Solution3 s = new Solution3();
        String s1 = "tmmzuxt";
//        System.out.println(s1.charAt(0) + ' ');
        int res = s.lengthOfLongestSubstring(s1);
        System.out.println(res);

        boolean b = true;
        System.out.println(b);
    }
}

class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;
        int[] freq = new int[256];  // 存256个ASCII 码出现的频率
        int l = 0, r = -1;  // 记录滑动窗口[l, r]
        int res = 0;
        while (l < s.length()) {
            if (r + 1 < s.length() && freq[s.charAt(++ r)] == 0) {
                // 说明没有重复字符
                freq[s.charAt(r)] ++;
            } else {
                while (s.charAt(l) != s.charAt(r)) {
                    freq[s.charAt(l)] --;
                    l ++;
                }
                l ++;
            }
            res = Math.max(res, r - l + 1);
        }
        // 说明字符串全是重复的字符
        if (res == 0)
            return 1;
        return res;

    }
}