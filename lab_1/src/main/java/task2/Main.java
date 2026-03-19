package task2;

import task2.util.BPTree;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BPTree<String> tree = new BPTree<>();

        List<String> trace = new ArrayList<>();
        tree.insert(10, "ten", trace);
        tree.insert(20, "twenty", trace);
        tree.insert(30, "thirty", trace);
        tree.insert(40, "forty", trace);
        tree.insert(50, "fifty", trace);
        tree.insert(60, "sixty", trace);
        tree.insert(70, "seventy", trace);

        System.out.println("Trace: " + trace);
        System.out.println("Keys: " + tree.getAllKeysInOrder());
        System.out.println("Height: " + tree.height());
        System.out.println("Search 40 = " + tree.search(40));
    }
}