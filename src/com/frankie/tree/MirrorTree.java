package com.frankie.tree;

import java.util.Stack;

/**
 * MirrorTree
 *
 * @author: Frankie
 */
public class MirrorTree {

    /**
     * 将二叉树变为镜像二叉树
     * @param root
     */
    public static void doMirrorTree(Node root) {
        if (root == null) {
            return;
        }
        Node tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        if (root.left != null) {
            doMirrorTree(root.left);
        }
        if (root.right != null) {
            doMirrorTree(root.right);
        }
    }

    public static void doMirrorTreeNoRecursive(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            if (root.left != null) {
                stack.push(root.left);
            }
            if (root.right!= null) {
                stack.push(root.right);
            }
            if (root.left != null || root.right != null) {
                Node tmp =root.left;
                root.left = root.right;
                root.right = tmp;
            }

        }
    }

    public static void preOrder(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            System.out.println(root.value);
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
    }
    public static void main(String[] args) {
        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        node.left = node1;
        node.right = node2;

        node1.left = node3;
        node1.right = node5;

        node2.right = node4;
        node2.left = node6;

        doMirrorTreeNoRecursive(node);
        preOrder(node);

    }

    public static boolean containsTree(Node root1,Node root2) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }

        boolean res = false;
        if (root1.value == root2.value) {
            res = doContains(root1, root2);
        }
        if (!res) {
            res = containsTree(root1.left,root2);
        }
        if (!res) {
            res = containsTree(root1.right,root2);
        }

        return res;
    }

    private static boolean doContains(Node root1,Node root2) {
        if (root1 == null) {
            return false;
        }
        if (root2 == null) {
            return true;
        }
        if (root1.value == root2.value && doContains(root1.left,root2.left) &&
        doContains(root1.right,root2.right)) {
            return true;
        }
        return false;
    }
}
