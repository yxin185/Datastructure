package indi.yxin.trie;

import java.util.TreeMap;

public class Trie {
    private class Node {
        public boolean isWord;
        public TreeMap<Character,Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }
    // 获取Trie中存储的单词的数量
    public int getSize() {
        return size;
    }

    // 向Trie中添加单词word
    public void addNR(String word) {
        Node curr = root;
        for (int i = 0;i < word.length();i++) {
            char c = word.charAt(i);
            if (curr.next.get(c) == null) {
                curr.next.put(c, new Node());
            }
            curr = curr.next.get(c);
        }
        // 遍历完一个单词，记录一下
        if (!curr.isWord) {
            curr.isWord = true;
            size ++;
        }
    }

    // 使用递归算法
    public void add(String word) {
        // 向以root为根的Trie中添加单词word
        add(root, word, 0);
    }

    /**
     * 递归写法调用方法实现Trie
     * @param node
     * @param word
     * @param index 因为递归的下一次要写入的单词比前一次少一个头，所以使用index来记录一下起始位置
     */
    private void add(Node node, String word, int index) {
        // 递归终止条件
        if (!node.isWord && index == word.length()) {
            node.isWord = true;
            size ++;
        }
        // 说明插入还没有结束
        if (word.length() > index) {
            // 要往Trie中添加的元素
            char c = word.charAt(index);
            if (node.next.get(c) == null) {
                node.next.put(c, new Node());
            }

            add(node.next.get(c), word, index + 1);
        }
    }
    // 判断一个单词是不是在一个Trie中
    public boolean containsNR(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.next.get(c) == null)
                return false;
            else
                curr = curr.next.get(c);
        }
        // 遍历完一个字符串，需要判断最后一个字符是不是一个单词
        // 如 pan 是包含于panda 的，所以需要判断n所在的节点是不是一个单词
        return curr.isWord;
    }

    public boolean contains(String word) {
        return contains(root, word, 0);
    }

    /**
     * 查询一个单词是不是在一个Trie中
     * @param node
     * @param word
     * @param index
     */
    private boolean contains(Node node, String word, int index) {
        // 递归终止条件
        if (word.length() == index)
            return node.isWord;

        char c = word.charAt(index);
        if (node.next.get(c) == null)
            return false;
        else
            return contains(node.next.get(c), word, index +1);
    }

    // 查询是否在Trie中有以单词prefix为前缀的单词
    public boolean isPrefix(String prefix) {
        Node curr = root;
        for (int i = 0;i < prefix.length();i ++) {
            char c = prefix.charAt(i);
            if (curr.next.get(c) == null)
                return false;

            curr = curr.next.get(c);
        }
        return true;
    }


}
