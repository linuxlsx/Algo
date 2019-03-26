package org.linuxlsx.algo.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Solution of <a href='https://leetcode.com/problems/longest-valid-parentheses/description/'>Longest Valid Parentheses</a>
 * @author linuxlsx
 * @date 2018/9/10
 */
public class LongestValidParentheses {

    /**
     * 解法1: 连续合法的()肯定在位置上是连续的，所以可以将此题转化为从一个有序的数组中找到最长连续序列的长度
     * 整体的时间复杂度为 2*O(n) + Olg(n)
     * 空间复杂度为  3*O(n)
     */
    public int longestValidParentheses(String s) {

        if (s == null || s.length() < 2) {
            return 0;
        }

        char[] stacks = new char[s.length()];
        int[] pIndex = new int[s.length()];

        //所有合法的 () 的起始位置和结束位置
        List<Integer> list = new ArrayList<>(s.length());

        int index = 0;

        //O(n) 次遍历
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            pIndex[index] = i;
            if (c == '(') {
                stacks[index++] = c;
            } else if (c == ')') {
                if (index > 0 && stacks[index - 1] == '(') {
                    //记录 ) 的索引
                    list.add(pIndex[index]);
                    index--;
                    //记录 ( 的索引
                    list.add(pIndex[index]);
                    stacks[index] = '0';
                }
            }
        }

        //Olg(n) 排序
        Collections.sort(list);

        int maxLength = 0;
        int tmp = 0;
        //O(n) 次遍历
        for (int i = 0; i < list.size(); i++) {
            if (i== 0 || list.get(i) - list.get(i - 1) == 1) {
                tmp++;
            } else {
                if (maxLength < tmp) {
                    maxLength = tmp;
                }
                tmp = 1;
            }
        }

        maxLength = (maxLength < tmp ? tmp : maxLength) ;

        return  maxLength;
    }

    /**
     * 只使用栈来处理数据。主要思路是:
     * 1. 碰到 ( 就入栈
     * 2. 碰到 ) 就出栈
     * 3. 如果出现了栈空的情况，肯定是 ) 的数量 > ( 的数量，否则栈肯定不会空。在这种情况下肯定是需要重新开始计数的，计数的起始位置就是当前的位置
     * 4. 如果栈不空就统计下到目前位置有效的长度
     *
     * 算法的时间复杂度: O(n)
     * 算法的空间复杂度: O(n)
     */
    public int longestValidParenthesesV2(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();

        //这个地方先插入一个-1 可以保证在字符串有效的情况下，计算出正确的数量。因为索引是从 0 开始的
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                //如果出现了栈空的情况，肯定是 ) 的数量 > ( 的数量，否则栈肯定不会空。
                //在这种情况下肯定是需要重新开始计数的，计数的起始位置就是当前的位置
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    //如果栈不空就统计下到目前位置有效的长度
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    public static void main(String[] args) {

        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();


        System.out.println(longestValidParentheses.longestValidParenthesesV2("()(())"));

    }
}
