package org.linuxlsx.algo.leetcode;

public class ReorderList {

    public void reorderList(ListNode head) {

        if (head == null || head.next == null) {
            return;
        }

        //get mid
        ListNode mid = getMid(head);
        //reverse right half
        ListNode second = reverse(mid);
        //merge list
        merge(head, second);

    }

    public void merge(ListNode first, ListNode second) {

        while (first != null && second != null) {
            ListNode fNext = first.next;
            ListNode sNext = second.next;

            first.next = second;
            if (fNext != null) {
                second.next = fNext;
            }

            first = fNext;
            second = sNext;
        }


    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;

    }

    //如果长度是奇数，则后半段长
    public ListNode getMid(ListNode head) {
        ListNode midPre = null;

        while (head != null && head.next != null) {
            midPre = midPre == null ? head : midPre.next;
            head = head.next.next;
        }

        ListNode mid = midPre.next;
        midPre.next = null;

        return mid;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ReorderList reorderList = new ReorderList();
        reorderList.reorderList(head);

        if (head != null) {
            do {
                System.out.println(head.val);
            }while ((head = head.next) != null);
        }else {
            System.out.println("null");
        }
    }
}
