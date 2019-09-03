package indi.yxin.Map;

import indi.yxin.util.FileOperation;

import java.util.ArrayList;

public class TestLinkedListMap {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("E:\\Java_SourceCode\\Datastructure\\src\\main\\resources\\pride-and-prejudice.txt", words)) {
            System.out.println("Total words : " + words.size());

            LinkedListMap<String, Integer> map = new LinkedListMap<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else
                    map.add(word, 1);
            }
            System.out.println("Total different words : " + map.getSize());
        }
    }
}
