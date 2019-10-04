package indi.yxin.LeetCode;

import java.util.LinkedList;
import java.util.List;

public class LetterCombination17 {
    public static void main(String[] args) {
        String digits = "23";
        Solution17 s = new Solution17();
        List<String> res = s.letterCombination(digits);
        System.out.println(res);
    }
}

class Solution17 {
    private String[] letterMap = {
            " ",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };

    private List<String> res;

    public List<String> letterCombination(String digits) {
        res = new LinkedList<>();
        if (digits.equals("")) {
            return res;
        }

        findCombinations(digits, 0, "");

        return res;
    }

    /**
     * s 中保存了此时从 digits[0,index - 1] 翻译得到的一个字母字符串
     * 寻找和 digits[index] 匹配的字母，获得 digits[0,index]翻译得到的解
     * @param digits
     * @param index
     * @param s
     */
    private void findCombinations(String digits, int index, String s) {
        if (index == digits.length()) {
            res.add(s);
            return;
        }

        // 获得 index 对应数字在字母表中的位置
        char c = digits.charAt(index);
        String string = letterMap[c - '0'];
        for (int i = 0;i < string.length();i ++) {
            findCombinations(digits, index + 1, s + string.charAt(i));
        }
    }

}
