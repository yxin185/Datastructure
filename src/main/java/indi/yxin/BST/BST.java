package indi.yxin.BST;

import java.util.*;

public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     向二分搜索树中插入元素，原始方法
      */
//    public void add(E e) {
//        if (root == null) {
//            root = new Node(e);
//            size ++;
//        } else
//            add(root, e);
//    }
//
//    // 向以node为根的二分搜索树中插入元素e，递归算法
//    private void add(Node node, E e) {
//
//        // 最基本情况,递归终止条件
//        if (e.equals(node.e)) {
//            return;
//        } else if (e.compareTo(node.e) < 0 && node.left == null) {
//            node.left = new Node(e);
//            size ++;
//            return;
//        } else if (e.compareTo(node.e) > 0 && node.right == null) {
//            node.right = new Node(e);
//            size ++;
//            return;
//        }
//
//        // 划分问题为更小的问题
//        if (e.compareTo(node.e) < 0) {
//            add(node.left, e);
//        } else
//            add(node.right, e);
//    }

    /**
     * 向二分搜索树中插入元素，优化方法
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     *
     * @param node
     * @param e
     * @return 返回插入新节点后二分搜索树的根
     */
    private Node add(Node node, E e) {
        // 递归终止条件
        if (node == null) {
            size ++;
            return new Node(e);
        }

        // 划分为更小的问题
        if (e.compareTo(node.e) < 0) {
            // 将左子树根节点作为新的根节点，在上面添加元素e
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    // 是否包含某一个元素e
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        // 基本情况
        if (node == null)
            return false;

        // 划分为更小的问题
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else // e.compareTp(node.e) > 0
            return contains(node.right, e);
    }

    // 前序遍历
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {

        if (node == null)
            return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 前序遍历非递归
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        if (root == null)
            return;
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            System.out.println(curr.e);

            if (curr.right != null)
                stack.push(curr.right);
            if (curr.left != null)
                stack.push(curr.left);
        }
    }

    // 中序遍历：结果是顺序的
    public void inOrder() {
        inOrder(root);
    }
    // 中序遍历以node为根的二分搜索树，递归算法
    private void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }
    // 中序非递归
    public void inOrderNR() {
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            System.out.println(curr.e);
            curr = curr.right;
        }
    }

    // 后序遍历：为二分搜索树释放内存
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    // 后序遍历非递归
    public void postOrderNR() {
        Stack<Node> stack = new Stack<>();
        List<E> list = new ArrayList<>();
        if (root == null)
            return;
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            list.add(curr.e);
            if (curr.left != null)
                stack.push(curr.left);
            if (curr.right != null)
                stack.push(curr.right);
        }
        Collections.reverse(list);
        System.out.println(list);
    }

    // 二叉树的层序遍历
    public void levelOrder() {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node curr = q.remove();
            System.out.println(curr.e);

            if (curr.left != null)
                q.add(curr.left);
            if (curr.right != null)
                q.add(curr.right);
        }
    }

    // 找到最小元素
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty.");
        }

        return minimum(root).e;
    }
    private Node minimum(Node node) {
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    // 找到最大元素
    public E maximum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty.");

        return maximum(root).e;
    }
    private Node maximum(Node node) {
        if (node.right == null)
            return node;

        return maximum(node.right);
    }

    // 删除最小元素
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }
    // 删除以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        // 最基本情况，终止条件
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 删除最大元素
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }
    private Node removeMax(Node node) {

        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }

        node.right = removeMax(node.left);
        return node;
    }

    // 删除二分搜索树中任意元素
    public void remove(E e) {
        root = remove(root, e);
    }
    // 删除以node为根的二分搜索树中值为e的节点，递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e) {
        if (node == null)
            return null;

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else { // e == node.e
            // 待删除节点左子树为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                return rightNode;
            }
            // 待删除元素右子树为空
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                return leftNode;
            }
            // 待删除元素左右子树都不为空
            // 找到比待删除节点大的最小节点，即待删除元素右子树中的最小节点
            // 用这个节点顶替待删除节点的位置
            // 方案一
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
            // 方案二：用比这个节点小的最大节点来代替待删除节点
//            Node previous = maximum(node.left);
//            previous.left = removeMax(node.left);
//            previous.right = node.right;
//            node.left = node.right = null;
//            return previous;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i< depth;i++) {
            res.append("--");
        }
        return res.toString();
    }
}
