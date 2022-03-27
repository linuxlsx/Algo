package org.linuxlsx.algo.leetcode;

public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode end = dummyHead;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }

            if(end == null){
                break;
            }

            ListNode start = pre.next;
            ListNode next = end.next;

            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;

        }

        return dummyHead.next;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null){
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }

        return pre;
    }


    public static void main(String[] args) {

    }
}
