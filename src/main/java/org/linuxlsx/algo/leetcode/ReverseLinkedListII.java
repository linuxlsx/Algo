package org.linuxlsx.algo.leetcode;


public class ReverseLinkedListII {

    public ListNode reverseBetweenV2(ListNode head, int m, int n) {
        ListNode result = new ListNode(0);
        ListNode curr = result;
        curr.next = head; // curr is the previous ListNode of head
        int count = 1; // we start from the head which is the first ListNode
        while (head != null) {
            if (count == m) {
                curr.next = reverseLinkedListV2(head, n - m + 1);
                break;
            }

            curr = curr.next;
            head = head.next;
            count++;
        }

        return result.next;
    }

    private ListNode reverseLinkedListV2(ListNode head, int k) {
        ListNode prev = null, curr = head, temp = null;
        while (k > 0) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
            k--;
        }

        // after finishing reversing,
        // connect the current tail (head) with the remaining ListNodes
        head.next = temp;
        return prev;
    }


    public ListNode reverseBetween(ListNode head, int left, int right) {

        if(head == null || head.next == null){
            return head;
        }

        ListNode subHead = null;
        ListNode subHeadPre = null;
        ListNode subTailNext = null;
        ListNode start = head;

        int n  = 1;
        while (start != null){

            if(n + 1 == left){
                subHeadPre = start;
            }

            if(n == left){
                subHead = start;
            }

            if(n == right){
                subTailNext = start.next;
                start.next = null;
                break;
            }

            n++;
            start = start.next;
        }

        if(subHeadPre != null){
            subHeadPre.next = null;
        }

        ListNode newSubHead = reverseList(subHead);

        if(subHeadPre != null){
            subHeadPre.next = newSubHead;
        }else {
            head = newSubHead;
        }

        subHead.next = subTailNext;


        return head;
    }


    public ListNode reverseList(ListNode head){

        if(head == null || head.next == null){
            return head;
        }

        ListNode next = head.next;
        ListNode tmp = null;
        head.next = null;
        while (next != null){

            tmp =  next.next;
            next.next = head;

            head = next;
            next = tmp;

        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
//        head.next.next.next.next.next = new ListNode(6);
//        head.next.next.next.next.next.next = new ListNode(7);

        ListNode newHead = new ReverseLinkedListII().reverseBetween(head, 1, 5);

        if (newHead != null) {
            do {
                System.out.println(newHead);
            }while ((newHead = newHead.next) != null);
        }else {
            System.out.println("null");
        }
    }
}
