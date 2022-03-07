package org.linuxlsx.algo.leetcode;

public class RotateList {


    /**
     * 利用循环移动的特点，通过取模计算可以计算出移动后的头尾节点
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k){

        if (head == null) {
            return head;
        }

        int n = 1;
        ListNode node = head;
        while (node.next != null){
            n++;
            node = node.next;
        }

        int nextHeadIndex = n - (k % n);

        //如果为0则说明实际没有发生变化
        if(nextHeadIndex == n){
            return head;
        }
        //暂时将链表组成一个单项的循环列表
        node.next = head;

        while (nextHeadIndex-- > 1){
            head= head.next;
        }

        node = head.next;
        head.next = null;

        return node;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        RotateList rotateList = new RotateList();
        ListNode newHead = rotateList.rotateRight(head, 6);

        do {
            System.out.println(newHead);
        }while ((newHead = newHead.next) != null);
    }

}
