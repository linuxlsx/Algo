package org.linuxlsx.algo.leetcode;

public class RemoveDuplicatesFromSortedList2 {

    public ListNode deleteDuplicatesV2(ListNode head) {
        if(head == null){
            return head;
        }

        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode curr = dummyHead;

        while (curr.next != null && curr.next.next != null){
            if(curr.next.val == curr.next.next.val){
                int x = curr.next.val;
                while (curr.next != null && curr.next.val == x){
                    curr.next = curr.next.next;
                }
            }else {
                curr = curr.next;
            }
        }

        return dummyHead.next;

    }

    public ListNode deleteDuplicates(ListNode head) {

        if(head == null){
            return head;
        }

        ListNode returnHead = null;
        ListNode first = null;

        int n = 1;
        while (head != null){
            if(head.next != null && head.val == head.next.val){
                n++;
            }else {
                if(n == 1){
                    //说明要切换
                    if(first == null){
                        first = head;
                        returnHead = first;
                    }else {
                        first.next = head;
                        first = first.next;
                    }
                }

                n = 1;
            }

            head = head.next;
        }

        if (first != null) {
            first.next = null;
        }

        return returnHead;
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next.next = new ListNode(4);

        RemoveDuplicatesFromSortedList2 deleteDuplicates = new RemoveDuplicatesFromSortedList2();
        ListNode newHead = deleteDuplicates.deleteDuplicates(head);

        if (newHead != null) {
            do {
                System.out.println(newHead);
            }while ((newHead = newHead.next) != null);
        }else {
            System.out.println("null");
        }

    }
}
