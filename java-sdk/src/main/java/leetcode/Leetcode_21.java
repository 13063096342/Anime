package leetcode;

/**
 * @author chenfh
 * 题目描述：
 * 归并两个有序的链表
 * <p>
 * 假设链表为 1→2→3→∅，我们想要把它改成 ∅←1←2←3。
 * @date 2021-06-07 10:25
 */
public class Leetcode_21 {
    public static void main(String[] args) {
        System.out.println(count0(25));
    }

    public static Integer count0(int n) {
        return n == 0 ? 0 : n / 5 + count0(n / 5);
    }

    public static ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (a.val > b.val) {
            b.next = mergeTwoLists(a.next, b);
            return b;
        } else {
            a.next = mergeTwoLists(a, b.next);
            return a;
        }
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
