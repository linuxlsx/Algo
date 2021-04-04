package org.linuxlsx.algo.leetcode;

public class RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicates(ListNode head) {

        if(head == null){
            return head;
        }

        ListNode first = head;
        ListNode second = head;
        while (second.next != null){
            if(second.next.val != first.val){
                //说明这里要做切换了
                first.next = second.next;
                first = second.next;
            }

            second = second.next;
        }

        first.next = null;

        return head;
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);

        RemoveDuplicatesFromSortedList  deleteDuplicates = new RemoveDuplicatesFromSortedList();
        ListNode newHead = deleteDuplicates.deleteDuplicates(head);

        do {
            System.out.println(newHead);
        }while ((newHead = newHead.next) != null);
    }
}
