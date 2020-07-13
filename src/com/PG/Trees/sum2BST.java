package com.PG.Trees;

import java.util.Stack;

public class sum2BST {
}

class TreeNode {
    int val;
    TreeNode left=null, right=null;

    public TreeNode(int val) {
        this.val = val;
    }
}

//REF: https://leetcode.com/problems/two-sum-iv-input-is-a-bst/discuss/106073/Java-Use-iterator
//https://leetcode.com/problems/two-sum-iv-input-is-a-bst/discuss/225041/Java-Solutions
class Iterator {
    boolean minIter = true;
    Stack<TreeNode> stack = new Stack<>();
    Iterator(TreeNode root, boolean min) {
        minIter = min;
        pull(root);
    }

    int next() {
        TreeNode node = stack.pop();
        if (minIter) {
            if (node.right != null) pull(node.right);
        } else {
            if (node.left != null) pull(node.left);
        }
        return node.val;
    }

    boolean hasNext() {
        return stack.isEmpty() == false;
    }

    int peek() {
        return stack.peek().val;
    }

    void pull(TreeNode node) {
        while(node != null) {
            stack.push(node);
            if (minIter) node = node.left;
            else node = node.right;
        }
    }
}

class Solution{
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        Iterator minIter = new Iterator(root, true);
        Iterator maxIter = new Iterator(root, false);
        while(minIter.hasNext() && maxIter.hasNext()) {
            if (minIter.peek() == maxIter.peek()) return false;

            if(minIter.peek() + maxIter.peek() == k) {
                return true;
            } else if (minIter.peek() + maxIter.peek() < k) {
                minIter.next();
            } else {
                maxIter.next();
            }
        }
        return false;
    }
}
