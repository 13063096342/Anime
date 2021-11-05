package com.java.sdk.test;

/**
 * @author chenfh
 * @date 2021-09-22 19:41
 */
public class Test0922 {

    public static void main(String[] args) {
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(2);
        ListNode l13 = new ListNode(4);
        l11.next = l12;
        l12.next = l13;

        ListNode l21 = new ListNode(1);
        ListNode l22 = new ListNode(3);
        ListNode l23 = new ListNode(4);
        l21.next = l22;
        l22.next = l23;

        ListNode result = mergeTwoLists(l11, l21);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }


    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        ListNode top = null;
        ListNode result = new ListNode();

        //l1和l2都不为空  升序
        while (l1 !=null && l2!=null ) {

            if(l1.val < l2.val) {
                result.next = l1;
                l1 = l1.next;
            } else {
                result.next = l2;
                l2 = l2.next;
            }
            if (top == null) {
                top = result;
            }
            result = result.next;
        }

        if (l1 == null) {
            result.next = l2;
        }
        if (l2 == null) {
            result.next = l1;
        }
        return top.next;
    }


        public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}


