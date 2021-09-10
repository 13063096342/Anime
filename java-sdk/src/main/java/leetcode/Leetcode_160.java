package leetcode;

/**
 * @author chenfh
 * 题目描述：
 * 找出两个链表的交点
 * 例如以下示例中 A 和 B 两个链表相交于 c1：
 * <p>
 * A:          a1 → a2
 * ↘
 * c1 → c2 → c3
 * ↗
 * B:    b1 → b2 → b3
 * @date 2021-06-07 10:25
 */
public class Leetcode_160 {
    public static void main(String[] args) {
        System.out.println(count0(25));
    }

    public static Integer count0(int n) {
        return n == 0 ? 0 : n / 5 + count0(n / 5);
    }

    /**
     * 当l1=l2=null时退出循环
     * l1 ->b  l2->a 是为了轮询相同长度
     */
    public static ListNode count0(ListNode a, ListNode b) {
        ListNode l1 = a;
        ListNode l2 = b;
        while (l1 != l2) {
            l1 = (l1 == null) ? b : l1.next;
            l2 = (l2 == null) ? a : l2.next;
        }
        return l1;
    }

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
