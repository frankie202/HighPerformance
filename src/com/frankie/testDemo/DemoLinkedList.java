package com.frankie.testDemo;

public class DemoLinkedList {
    static class Node {
        int value;
        Node next;
    }

    public static Node inversion(Node head) {
        Node next = null;
        Node temp = null;

        while (head != null) {
            next = head.next;
            head.next = temp;
            temp = head;
            head = next;
        }
        return temp;
    }

    public static void show(Node head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        Node head = new Node();
        Node head1 = new Node();
        Node head2 = new Node();
        Node head3 = new Node();
        Node head4 = new Node();
        head1.value = 1;
        head2.value = 2;
        head3.value = 3;
        head4.value = 4;
        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;

        Node node = inversion(head1);

        show(node);
    }


}
