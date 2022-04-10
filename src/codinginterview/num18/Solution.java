package codinginterview.num18;

/**
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * 返回删除后的链表的头节点。
 *
 */

public class Solution {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */

    ListNode pre;
    ListNode firstNode;

    public ListNode deleteNode(ListNode head, int val) {
        firstNode = head;
        while (head != null) {
            if (head.val == val) {
                if (pre == null) {
                    return head.next;
                }
                pre.next = head.next;
                return firstNode;
            }
            pre = head;
            head = head.next;
        }
        return firstNode;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
