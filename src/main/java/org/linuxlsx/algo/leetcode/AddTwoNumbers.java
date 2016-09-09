package org.linuxlsx.algo.leetcode;

/**
 * Solution of  <a href="https://leetcode.com/problems/add-two-numbers/">Add Two Numbers</a>
 *
 * Created by linuxlsx on 16/9/9.
 */
public class AddTwoNumbers {

    /**
     * 思路: 注意给到的列表中数字的顺序其实是个位->百位->千位->...的顺序排列的,
     * 所以只要按照顺序将两个列表中对应位置的数字相加然后保存到另外一个列表中即可,
     * 需要注意在加法中会有一个进位的情况, 需要在求和中加上进位即可。
     *
     *<ul>
     *     <li>算法复杂度 O(n) n为最长列表的长度</li>
     *     <li>空间复杂度 O(n+1) n为最长列表的长度</li>
     *</ul>
     *
     * @see <a href="https://leetcode.com/articles/add-two-numbers/">Solution</a>
     *
     * @param first     第一个列表
     * @param second    第二个列表
     * @return          返回最终结果列表
     */
    public ListNode addTwoNumbers(ListNode first, ListNode second) {

        ListNode curr = new ListNode(0);
        ListNode node = curr;

        ListNode fNext = first;
        ListNode sNext = second;

        int stepIn = 0;

        while (fNext != null || sNext != null){

            int sum = 0;
            if(fNext != null){
                sum += fNext.val;
                fNext = fNext.next;
            }

            if(sNext != null){
                sum += sNext.val;
                sNext = sNext.next;
            }

            sum += stepIn;

            stepIn = (sum + stepIn) / 10;
            curr.next = new ListNode(sum % 10);

            curr = curr.next;
        }

        if(stepIn > 0) {
            curr.next = new ListNode(stepIn);
        }

        return node.next;

    }

    public static void main(String[] args) {

        ListNode first = new ListNode(2);
        first.next = new ListNode(4);
        first.next.next = new ListNode(3);

        ListNode second = new ListNode(5);
        second.next = new ListNode(6);
        second.next.next = new ListNode(4);
        second.next.next.next = new ListNode(6);
        second.next.next.next.next = new ListNode(6);
        second.next.next.next.next.next = new ListNode(6);
        second.next.next.next.next.next.next = new ListNode(6);
        second.next.next.next.next.next.next.next = new ListNode(6);


        AddTwoNumbers solution = new AddTwoNumbers();

        System.out.println(solution.addTwoNumbers(first, second));

    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {

            String tmp = "" + val ;

            if(next != null){
                tmp = tmp + " -> " + next.toString();
            }

            return tmp;
        }
    }
}
