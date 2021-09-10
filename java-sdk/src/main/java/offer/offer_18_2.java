package offer;

import leetcode.Leetcode_160;

/**
 * 题目描述：删除链表中重复的结点
 *
 * @author chenfh
 * @date 2021-08-20 17:23
 */
public class offer_18_2 {

    public static void main(String[] args) {
        Leetcode_160.ListNode node1 = new Leetcode_160.ListNode(1);
        Leetcode_160.ListNode node2 = new Leetcode_160.ListNode(3);
        Leetcode_160.ListNode node3 = new Leetcode_160.ListNode(3);
        Leetcode_160.ListNode node4 = new Leetcode_160.ListNode(3);
        Leetcode_160.ListNode node5 = new Leetcode_160.ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Leetcode_160.ListNode listNode = deleteDuplication(node1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public static Leetcode_160.ListNode deleteDuplication(Leetcode_160.ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        Leetcode_160.ListNode next = pHead.next;

        if (pHead.val == next.val) {
            next = next.next;
            return deleteDuplication(next);
        }
        pHead.next = deleteDuplication(next);
        return pHead;
    }
}
