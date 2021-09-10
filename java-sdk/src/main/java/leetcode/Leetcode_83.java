package leetcode;

/**
 * @author chenfh
 * 题目描述：
 * 从有序链表中删除重复节点
 * <p>
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 * @date 2021-06-07 10:25
 */
public class Leetcode_83 {
    public static void main(String[] args) {
        System.out.println(count0(25));
    }

    public static Integer count0(int n) {
        return n == 0 ? 0 : n / 5 + count0(n / 5);
    }

    public static ListNode deleteDuplicates(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        node.next = deleteDuplicates(node.next);
        return node.val == node.next.val ? node.next : node;

    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
