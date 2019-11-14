package indi.yxin.LeetCode;

import java.util.Stack;

public class MinStack155 {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(0);
        minStack.push(3);
        minStack.push(0);
        System.out.println("最小值：" + minStack.getMin());
        minStack.pop();
        System.out.println("最小值：" + minStack.getMin());
        minStack.pop();
        System.out.println("最小值：" + minStack.getMin());
        minStack.pop();
        System.out.println("最小值：" + minStack.getMin());
    }
}

class MinStack {
    private Stack<Integer> data;
    private Stack<Integer> min_stack;   // 辅助栈，存最小元素

    /** initialize your data structure here. */
    public MinStack() {
        data = new Stack<>();
        min_stack = new Stack<>();
    }

    public void push(int x) {
        if (data.isEmpty()) {
            data.push(x);
            min_stack.push(x);
        } else {
            data.push(x);
            if (x <= min_stack.peek())
                min_stack.push(x);
        }
    }

    public void pop() {
        if (data.pop().equals(min_stack.peek()))
            min_stack.pop();
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return min_stack.peek();
    }
}
