package codinginterview.num35;

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        while (cur != null) {
            Node newNode = new Node(cur.val);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = newNode.next;
        }
        cur = head;
        while (cur != null) {
            Node next = cur.next;
            if (cur.random != null) {
                next.random = cur.random.next;
            } else {
                next.random = null;
            }

            cur = cur.next.next;
        }
        cur = head;
        Node result = head.next;
        while (cur != null) {
            Node next = cur.next;
            cur.next = cur.next.next;
            if (next.next == null) {
                next.next = null;
            } else {
                next.next = next.next.next;
            }

            cur = cur.next;
        }
        return result;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}