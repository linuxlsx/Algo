package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href="https://leetcode.com/problems/swap-nodes-in-pairs/description/">Swap Nodes in Pairs</a>
 * @author linuxlsx
 * @date 2018/7/16
 */
public class SwapNodesInPairs {


    public ListNode swapPairs(ListNode head) {

        if(head.next == null){
            return head;
        }

        ListNode alwaysHead = head.next;

        ListNode floatHead = null;
        ListNode tmp1 = head;
        ListNode tmp2 = tmp1.next != null ? tmp1.next : null;

        while (tmp2 != null){

            if(floatHead != null){
                floatHead.next = tmp2;
            }
            tmp1.next = tmp2.next;
            tmp2.next = tmp1;

            floatHead = tmp1;
            tmp1 = tmp1.next;
            if (tmp1 != null) {
                tmp2 = tmp1.next;
            }else {
                tmp2 = null;
            }

        }


        return alwaysHead;
    }

    public ListNode swapPairs4(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }



    public ListNode swapPairs2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs2(next.next);
        next.next = head;
        return next;
    }



    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(6);
        ListNode seven = new ListNode(7);
        ListNode eight = new ListNode(8);
        ListNode nine = new ListNode(9);

        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        six.next = seven;
        seven.next = eight;
        eight.next = nine;


        SwapNodesInPairs swapNodesInPairs = new SwapNodesInPairs();
        ListNode listNode = swapNodesInPairs.swapPairs4(one);


        StringBuilder sb = new StringBuilder();

        while (listNode != null){
            sb.append(listNode.val).append( " -> ");

            listNode = listNode.next;
        }

        System.out.println(sb.toString());

    }

}


