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

    public  ListNode mergeKListsV2(ListNode[] lists){
        return partition(lists,0,lists.length-1);
    }

    public  ListNode partition(ListNode[] lists,int s,int e){
        if(s==e)  return lists[s];
        if(s<e){
            int q=(s+e)/2;
            ListNode l1=partition(lists,s,q);
            ListNode l2=partition(lists,q+1,e);
            return merge(l1,l2);
        }else
            return null;
    }

    //This function is from Merge Two Sorted Lists.
    public ListNode merge(ListNode l1,ListNode l2){
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val<l2.val){
            l1.next=merge(l1.next,l2);
            return l1;
        }else{
            l2.next=merge(l1,l2.next);
            return l2;
        }
    }

    public ListNode mergeKListsV3(ListNode[] lists) {
        return splitAndSort(lists, 0, lists.length - 1);
    }

    public ListNode splitAndSort(ListNode[] lists, int start, int end){
        if(start == end ){
            return lists[start];
        }

        int mid = (start + end) / 2;
        ListNode left = splitAndSort(lists, start, mid);
        ListNode right = splitAndSort(lists, mid + 1, end);

        return mergeV2(left, right);
    }

    public ListNode mergeV2(ListNode left, ListNode right){
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;

        while(left != null && right != null){
            if(left.val <= right.val){
                tail.next = left;
                left = left.next;
            }else{
                tail.next = right;
                right = right.next;
            }
        }

        tail.next = left == null ? right : left;
        return dummyHead.next;
    }

    public static void main(String[] args) {

        ListNode one = new ListNode(1);
        ListNode two = new ListNode(4);
        ListNode three = new ListNode(5);

        one.next = two;
        two.next = three;

        ListNode four = new ListNode(1);
        ListNode five = new ListNode(3);
        ListNode six = new ListNode(4);

        four.next = five;
        five.next = six;

        ListNode seven = new ListNode(2);
        ListNode eight = new ListNode(6);
        //ListNode nine = new ListNode(9);
        seven.next = eight;
        //eight.next = nine;

        ListNode[] listNodes = {one, four, seven};

        MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();



        ListNode listNode = mergeKSortedLists.mergeKListsV3(listNodes);

        StringBuilder sb = new StringBuilder();

        while (listNode != null){
            sb.append(listNode.val).append( " -> ");

            listNode = listNode.next;
        }

        System.out.println(sb.toString());

    }


}
