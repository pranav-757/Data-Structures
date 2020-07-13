package com.PG.Trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeMain {
    public static void main(String[] args) {
        BinaryTree1ToN tree = new BinaryTree1ToN();

        List<Node> list =  tree.constructTree(1,3);

        for (Node n: list)
        {
            tree.preorder(n);
            System.out.println("\n");
        }


    }
}
