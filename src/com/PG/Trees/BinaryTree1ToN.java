package com.PG.Trees;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree1ToN {
//    class Node {
//        int val;
//        Node left, right;
//
//        Node(int val) {
//            this.val = val;
//            left = null;
//            right = null;
//        }
//    }

    public List<Node> constructTree(int start, int end) {

        ArrayList<Node> list = new ArrayList<>();

        if(start > end) {
            list.add(null);
            return  list;
        }

        for(int i=start; i<=end; i++) {
            List<Node> leftSubTrees = constructTree(start, i-1);
            List<Node> rightSbtrees = constructTree( i+1, end);

            for(int j=0;j<leftSubTrees.size();j++){
                Node left = leftSubTrees.get(j);

                for(int k=0;k<rightSbtrees.size();k++){
                    Node root = new Node(i);
                    root.left = left;
                    root.right = rightSbtrees.get(k);

                    list.add(root);
                }
            }
        }
        return list;
    }

    public static void preorder(Node root)
    {
        if (root != null)
        {
            System.out.print(root.val+" ") ;
            preorder(root.left);
            preorder(root.right);
        }
    }
}
