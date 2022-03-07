package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href="https://leetcode.com/problems/merge-k-sorted-lists/description/">Merge k Sorted Lists</a>
 *
 * @author linuxlsx
 * @date 2018/7/6
 */
public class MergeKSortedLists {

    /**
     * 笨办法1: 根据 MergeTwoSortedList中的方法，每次都是两两的merge , 得到最终的结果
     * 笨办法2: 将所有的元素都保存到一个列表中，然后对这个列表做排序
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {

        if(lists.length == 1){
            return lists[0];
        }

        ListNode first = lists[0];
        for (int i = 1; i < lists.length ; i++) {
            first = mergeTwoLists(first, lists[i]);
        }

        return first;

    }

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

        ListNode seven = new ListNode(3);
        ListNode eight = new ListNode(8);
        ListNode nine = new ListNode(9);
        seven.next = eight;
        eight.next = nine;

        ListNode[] listNodes = {one, four, seven};

        MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();



        ListNode listNode = mergeKSortedLists.mergeKLists(listNodes);

        StringBuilder sb = new StringBuilder();

        while (listNode != null){
            sb.append(listNode.val).append( " -> ");

            listNode = listNode.next;
        }

        System.out.println(sb.toString());

    }


}
