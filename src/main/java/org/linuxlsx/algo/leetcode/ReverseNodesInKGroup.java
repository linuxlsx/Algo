package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href="https://leetcode.com/problems/reverse-nodes-in-k-group/description/">Reverse Nodes in k-Group</a>
 *
 * @author linuxlsx
 * @date 2018/7/24
 */
public class ReverseNodesInKGroup {

    /**
     * 思路是采用一个长度为 k 的数组来保存反转后的 k个元素，然后在将这个k个元素与原来的链表建立联系。
     * 当剩下的元素不足 k 个时直接放弃操作。
     *
     * 总共需要的空间为 : O(k + 4)
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        //创建一个包含k个元素的数组
        ListNode[] listNodes = new ListNode[k];
        //这个是数组元素中的前一个元素
        ListNode floatHead = null;
        //这个是最终需要返回的节点
        ListNode returnHead = null;
        //这个是为了在 k > head.length 的情况下直接返回 head
        ListNode originHead = head;
        //这个参数 true 表示列表剩余node 数 < k, 不需要做任何操作
        boolean end = false;

        while (head != null){
            for (int n = k - 1; n >= 0; n--){
                //在这里直接将节点交换顺序
                listNodes[n] = head;
                head = head.next;
                //这里表示已经到结尾了
                if(head == null){
                    if(n > 0){
                        end = true;
                    }
                    break;
                }
            }

            if(!end){
                //这里表示还没有到结尾
                //这里将数组中的元素建立关联
                for (int j = 0; j < listNodes.length - 1; j++) {
                    listNodes[j].next = listNodes[j+1];
                }

                //与链表后面的部分建立关联
                listNodes[listNodes.length - 1].next = head;

                //找到需要返回的节点，其实就是第一次交换后的第一个节点。
                if(returnHead == null){
                    returnHead = listNodes[0];
                }

                //与链表前面的部分建立关联
                if(floatHead != null){
                    floatHead.next = listNodes[0];
                }

                floatHead = listNodes[listNodes.length - 1];
            }
        }

        return returnHead == null ? originHead : returnHead;
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
        ListNode ten = new ListNode(10);

        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        //five.next = six;
        six.next = seven;
        seven.next = eight;
        eight.next = nine;
        nine.next = ten;


        ReverseNodesInKGroup reverseNodesInKGroup = new ReverseNodesInKGroup();
        ListNode listNode = reverseNodesInKGroup.reverseKGroup(one, 2);


        StringBuilder sb = new StringBuilder();

        while (listNode != null){
            sb.append(listNode.val).append( " -> ");

            listNode = listNode.next;
        }

        System.out.println(sb.toString());

    }
}
