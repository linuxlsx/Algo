package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href="https://leetcode.com/problems/merge-two-sorted-lists/description/">Merge Two Sorted Lists</a>
 *
 * @author linuxlsx
 * @date 2018/7/2
 */
public class MergeTwoSortedList {

    /**
     * 这个比较简单，从头开始遍历就好了。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(-1);
        ListNode node = head;

        ListNode first = l1;
        ListNode second = l2;

        while (first != null || second != null){

            if(first != null && (second == null || first.val <= second.val)){
                node.next = first;
                node = first;
                first = first.next;
            }else if(second != null) {
                node.next = second;
                node = second;
                second = second.next;
            }

        }

        return head.next;
    }


    public static void main(String[] args) {

        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(4);

        one.next = two;
        two.next = three;

        ListNode four = new ListNode(1);
        ListNode five = new ListNode(3);
        ListNode six = new ListNode(4);

        four.next = five;
        five.next = six;

        MergeTwoSortedList mergeTwoSortedList = new MergeTwoSortedList();

        ListNode listNode = mergeTwoSortedList.mergeTwoLists(one, four);

        StringBuilder sb = new StringBuilder();

        while (listNode != null){
            sb.append(listNode.val).append( " -> ");

            listNode = listNode.next;
        }

        System.out.println(sb.toString());

    }


}


