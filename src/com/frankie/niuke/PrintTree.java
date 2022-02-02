package com.frankie.niuke;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * PrintTree
 *
 * @author: Frankie
 */
public class PrintTree {

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(pRoot == null) {
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<TreeNode> stack2 = new LinkedList<>();
        stack.push(pRoot);
        TreeNode node  =null;
        boolean l2r = true;
        ArrayList<Integer> tmp = new ArrayList<>();
        while(!stack.isEmpty() || !stack2.isEmpty()) {
            if(l2r) {
                node = stack.pop();
                if (node.left != null) {
                    stack2.push(node.left);
                }
                if(node.right != null) {
                    stack2.push(node.right);
                }
                tmp.add(node.val);
                if(stack.isEmpty()) {
                    l2r = !l2r;
                    result.add(tmp);
                    tmp = new ArrayList<>();
                }
            } else {
                node = stack2.pop();
                if(node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
                tmp.add(node.val);
                if(stack2.isEmpty()) {
                    l2r = !l2r;
                    result.add(tmp);
                    tmp = new ArrayList<>();
                }
            }
        }
        return result;
    }

}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
