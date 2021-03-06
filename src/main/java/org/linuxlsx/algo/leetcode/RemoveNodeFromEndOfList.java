package org.linuxlsx.algo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Solution of <a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/">Remove Nth Node
 * From End of List</a>
 *
 * Given n will always be valid.
 * Try to do this in one pass.
 *
 * @author linuxlsx
 * @date 2017/12/15
 */
public class RemoveNodeFromEndOfList {

    /**
     * 题目中要求只循环一次，所以最简单的就是在一次遍历中将列表转换成为数组。然后在通过索引计算得到删除后的列表
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> listNodes = new ArrayList<ListNode>();

        ListNode tmp = head;
        while (tmp != null) {
            listNodes.add(tmp);
            tmp = tmp.next;
        }

        //表示删除的是第一个节点
        if (n == listNodes.size()) {
            if (listNodes.size() > 1) {
                return listNodes.get(1);
            } else {
                return null;
            }
        }

        int pre = listNodes.size() - n - 1;
        int last = listNodes.size() - (n - 1);

        listNodes.get(pre).next = (n == 1 ? null : listNodes.get(last));

        return listNodes.get(0);
    }

    /**
     * 前面的解法额外使用了 O(n) 的空间。通过使用 前后两个索引的方式只需要引入三个变量就可以了
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEndV2(ListNode head, int n) {

        ListNode headNode = new ListNode(-1);
        headNode.next = head;
        ListNode fastNode = headNode;
        ListNode slowNode = headNode;
        while (fastNode.next != null) {
            if (n <= 0) {
                slowNode = slowNode.next;
            }
            fastNode = fastNode.next;
            n--;
        }

        //slowNode.children != null 那么 slowNode.children 就是要删除的节点
        if (slowNode.next != null) {
            slowNode.next = slowNode.next.next;
        }
        return headNode.next;

    }

    public ListNode removeNthFromEndV3(ListNode head, int n) {
        ListNode node = head;
        Map<Integer, ListNode> keepMap = new HashMap<Integer, ListNode>();
        int count = 0;
        while (node.next != null) {
            keepMap.put(count, node);

            count ++;
            node = node.next;
        }

        if (n < count) {
            keepMap.get(count - n - 1).next = keepMap.get(count - n + 1);
        } else if (n == count) {
            head = keepMap.get(1);
        } else {
            System.out.println("n cannot be larger than List size.");
        }

        return head;

    }

    public ListNode reverseList(ListNode head){


        //initialization
        ListNode previousNode = null;
        ListNode nextNode = null;

        while (head != null) {
            //save the children node
            nextNode = head.next;
            //update the value of "children"
            head.next = previousNode;
            //shift the pointers
            previousNode = head;
            head = nextNode;
        }
        return previousNode;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        RemoveNodeFromEndOfList removeNodeFromEndOfList = new RemoveNodeFromEndOfList();
        ListNode listNodeResult = removeNodeFromEndOfList.removeNthFromEndV2(listNode, 5);
        //ListNode result = removeNodeFromEndOfList.reverseList(listNode);

        while (listNodeResult != null) {
            System.out.print(listNodeResult.val + " ");

            listNodeResult = listNodeResult.next;
        }

    }
}
