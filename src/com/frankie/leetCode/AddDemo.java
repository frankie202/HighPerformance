package com.frankie.leetCode;

public class AddDemo {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = l1.val + l2.val;
        int val = sum % 10;
        int flag = sum / 10;
        ListNode root = new ListNode(val);
        ListNode next =root;
        l1 = l1.next;
        l2 = l2.next;
        while (l1  != null && l2 != null) {
            sum =  l1.val + l2.val + flag;
            val = sum % 10;
            ListNode tmp = new ListNode(val);
            flag = sum / 10;
            next.next = tmp;
            next = tmp;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 != null) {
            sum =  l1.val + flag;
            val = sum % 10;
            ListNode tmp = new ListNode(val);
            flag = sum / 10;
            next.next = tmp;
            next = tmp;
            l1 = l1.next;
        }

        while(l2 != null) {
            sum =  l2.val + flag;
            val = sum % 10;
            ListNode tmp = new ListNode(val);
            flag = sum / 10;
            next.next = tmp;
            next = tmp;
            l2 = l2.next;
        }
        if (flag == 1) {
            next.next = new ListNode(flag);
        }
        return root;

    }
}
