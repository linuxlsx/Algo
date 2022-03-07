package org.linuxlsx.algo.leetcode;

public class PartitionList {

    /**
     * 时间复杂度 O(n)
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {

        if (head == null || head.next == null) {
            return null;
        }

        ListNode swap = null;
        ListNode first = new ListNode(-1, head);

        //找到列表中的插入点
        while (first.next != null){
            if(first.next.val >= x){
                break;
            }
            first = first.next;
        }

        ListNode node = first.next;

        while (node != null){
            if(node.next != null){

                if(node.next.val < x){
                    //将node.next 和 first.next 交换
                    swap = node.next;
                    node.next = node.next.next;
                    swap.next = first.next;

                    //这里判断下如果交换的是head
                    if(first.next == head){
                        head = swap;
                    }
                    first.next = swap;
                    first = swap;
                }else {
                    node = node.next;
                }
            }else {
                break;
            }
        }

        return head;
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(2);
        head.next = new ListNode(1);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(2);
//        head.next.next.next.next = new ListNode(5);
//        head.next.next.next.next.next = new ListNode(2);
//        head.next.next.next.next.next.next = new ListNode(1);
//
        PartitionList partitionList = new PartitionList();
        ListNode newHead = partitionList.partition(head, 2);

        if (newHead != null) {
            do {
                System.out.println(newHead);
            }while ((newHead = newHead.next) != null);
        }else {
            System.out.println("null");
        }
    }
}

