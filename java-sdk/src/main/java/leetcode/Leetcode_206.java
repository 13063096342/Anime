package leetcode;

/**
 * @author chenfh
 * 题目描述：
 * 反转链表
 * <p>
 * 假设链表为 1→2→3→∅，我们想要把它改成 ∅←1←2←3。
 * @date 2021-06-07 10:25
 */
public class Leetcode_206 {
    public static void main(String[] args) {
        System.out.println(count0(25));
    }

    public static Integer count0(int n) {
        return n == 0 ? 0 : n / 5 + count0(n / 5);
    }

    /**
     *
     */
    public static ListNode count0(ListNode a) {
        ListNode current = a;
        ListNode pre = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = pre;

            pre = current;
            current = next;
        }
        return pre;
    }

    /**
     * 头插法
     */
    public ListNode ReverseList(ListNode head) {
        ListNode newList = new ListNode(-1);
        while (head != null) {
            ListNode next = head.next;
            head.next = newList.next;
            newList.next = head;
            head = next;
        }
        return newList.next;
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
