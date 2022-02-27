package com.frankie.newCoder;

import java.util.ArrayList;

/**
 * Solution1
 *
 * @author: Frankie
 */
public class Solution1 {
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        if(lists == null || lists.isEmpty()) {
            return null;
        }
        if(lists.size() == 1) {
            return lists.get(0);
        }
        return this.doMerge2List(lists,1,lists.get(0));
    }

    private ListNode doMerge2List(ArrayList<ListNode> lists, int cnt, ListNode cur) {
        if (cnt == lists.size()) {
            return cur;
        }
        ListNode head = null;
        ListNode tmp = null;
        ListNode node = null;
        ListNode next = lists.get(cnt);
        while (cur != null && next != null) {
            if(cur.val < next.val) {
                node = cur.next;
                if(head == null) {
                    head = cur;
                    tmp = head;
                } else {
                    tmp.next = cur;
                    tmp = cur;
                }
                cur.next = null;
                cur = node;
            } else {
                node = next.next;
                if(head == null) {
                    head = next;
                    tmp = head;
                } else {
                    tmp.next = next;
                    tmp = next;
                }
                next.next = null;
                next = node;
            }
        }
        if (cur != null) {
            tmp.next = cur;
        }
        if (next != null) {
            tmp.next = next;
        }
        return this.doMerge2List(lists,cnt + 1,head);
    }

    public static void main(String[] args) {
        ArrayList<ListNode> list = new ArrayList<>();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node4.next = node5;
        ListNode node6 = new ListNode(6);
        node5.next = node6;
        ListNode node7 = new ListNode(7);
        node6.next = node7;
        list.add(node1);
        list.add(node4);
        Solution1 solution1 = new Solution1();
        ListNode node = solution1.mergeKLists(list);

        System.out.println();
    }
}
