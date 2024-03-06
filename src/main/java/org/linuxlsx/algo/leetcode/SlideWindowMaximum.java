package org.linuxlsx.algo.leetcode;



import java.util.ArrayDeque;
import java.util.Deque;

public class SlideWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {

        int[] res = new int[nums.length - k + 1];
        int index = 0;

        //使用双端队列来保存数组的下标。存下标可以更好的判断元素是否需要被移除出队列
        //需要保证队列的队首是窗口中最大的元素
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {

            // 5 8 9 10 11 12 13 14
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            if (deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            if (i >= k - 1) {
                res[index++] = nums[deque.peekFirst()];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        SlideWindowMaximum maximum = new SlideWindowMaximum();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};

        System.out.println(maximum.maxSlidingWindow(nums, 3));
    }
}
