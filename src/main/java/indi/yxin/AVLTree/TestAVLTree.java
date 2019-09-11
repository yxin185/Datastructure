package indi.yxin.AVLTree;

import indi.yxin.util.FileOperation;

import java.util.ArrayList;

public class TestAVLTree {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("E:\\Java_SourceCode\\Datastructure\\src\\main\\resources\\pride-and-prejudice.txt", words)) {
            System.out.println("Total words : " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else
                    map.add(word, 1);
            }

            System.out.println("Total different words : " + map.getSize());
            System.out.println("Frequency of PRIDE : " + map.get("pride"));
            System.out.println("isBStTree? : " + map.isBST());
            System.out.println("isBalancedTree? : " + map.isBalanced());
        }
    }
}
