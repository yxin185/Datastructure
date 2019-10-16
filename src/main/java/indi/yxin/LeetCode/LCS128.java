package indi.yxin.LeetCode;

import java.util.Arrays;

// Longest Consecutive Sequence
public class LCS128 {
    public static void main(String[] args) {
        Solution128 s = new Solution128();
        int[] arr = {0,0,0,0,1,2,2,2};
        int res = s.longestConsecutive(arr);
        System.out.println(res);
    }
}

class Solution128 {
    // 使用并查集
    private int[] parent;
    private int[] sz; // sz[i] 用于记录以 i 为根节点的元素个数

    public int longestConsecutive(int[] nums) {
        // 先对数组进行排序，再进行并查集操作
        Arrays.sort(nums);
        parent = new int[nums.length];
        sz = new int[nums.length];
        for (int i = 0;i < nums.length;i ++) {
            parent[i] = i;
            sz[i] = 1;
        }
        // 在两个序列连续时，才进行并操作
        // 对于数组中的重复元素，要能够处理
        for (int i = 0;i < nums.length - 1;i ++) {
            int j = i + 1; // j 代表 i后面一个元素
            while (nums[i] == nums[j]) {
                // 如果 j == nums.length - 1成立，说明从 i 开始到数组结尾的元素全部相等，直接 break
                if (j == nums.length - 1)
                    break;
                j += 1;
            }

            if (nums[i] + 1 == nums[j])
                unionElements(i, j);
            // 相当于将 i 的位置直接移到下一个不为 nums[i] 的位置，因为马上要 i ++
            // 所以将 i = j - 1
            i = j - 1;
        }

        int res = 0;
        for (int i = 0;i < sz.length;i ++) {
            if (sz[i] > res)
                res = sz[i];
        }
        return res;
    }

    private void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;

        if (sz[pRoot] > sz[qRoot]) {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[pRoot] = qRoot;
            sz[qRoot] += 1;
        }
    }

    private int find(int p) {
        // TODO: if p is legal
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
}
