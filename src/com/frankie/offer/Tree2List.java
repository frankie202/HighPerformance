package com.frankie.offer;

import java.util.LinkedList;

/**
 * Tree2List
 * Description:
 *  将二叉树转换为双向链表
 * @Author Frankie
 * @Date 2022/6/8
 */
public class Tree2List {

    /**
     * 二叉树转换为双向链表，
     * 其中left 表示指向前一个节点的指针，right表示指向后一个节点的指针
     * 使用中序遍历
     * @param root
     * @return 链表的头节点
     */
    public static Node getFirstNode(Node root) {
        if (root == null) {
            return null;
        }
        Node head = null;
        Node tmp = null;
        LinkedList<Node> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (tmp == null) {
                tmp = root;
                head = root;
            } else {
                tmp.right = root;
                root.left = tmp;
                tmp = root;
            }
            root = root.right;
        }
        return head;
    }

    public static void main(String[] args) {
        Node tree = createTree();
        Node node = getFirstNode(tree);

        System.out.println(node);
    }

    private static Node createTree() {
        Node root = new Node(6);
        Node node1 = new Node(4);
        Node node2 = new Node(5);
        Node node3 = new Node(7);
        Node node4 = new Node(8);
        root.left =node1;
        root.right = node3;
        node1.left = node2;
        node3.left = node4;
        return root;
    }
    static class Node {
        int value;
        Node left;
        Node right;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }
    }
}
