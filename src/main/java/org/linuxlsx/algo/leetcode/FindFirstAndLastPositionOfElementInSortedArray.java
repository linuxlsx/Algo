package org.linuxlsx.algo.leetcode;

import java.util.Arrays;

/**
 * Solution of <a href='https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/'>Find First and Last Position of Element in Sorted Array</a>
 * <p>
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * If the target is not found in the array, return [-1, -1].
 *
 * @author linuxlsx
 * @date 2018-12-18
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    /**
     * 可以通过执行两次BinarySearch来满足 log(n)的时间复杂度需求。
     * 与普通BinarySearch不同的，当 mid == target 的时候需要继续
     * 向左或者向右继续搜索，来找到最左和最右的边界。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {

        int[] a = {-1, -1};

        a[0] = search(nums, target, 0, nums.length - 1, true);
        a[1] = search(nums, target, 0, nums.length - 1, false);

        return a;
    }

    public int search(int[] nums, int target, int start, int end, boolean left) {

        int p = -1;

        if (start <= end) {
            int mid = (start + end) >>> 1;
            int midVal = nums[mid];

            if (midVal == target) {
                p = mid;
                if (left) {
                    int tmp = search(nums, target, start, mid - 1, true);
                    if (tmp != -1 && tmp < p) {
                        p = tmp;
                    }
                } else {
                    int tmp = search(nums, target, mid + 1, end, false);
                    if (tmp != -1 && tmp > p) {
                        p = tmp;
                    }
                }

            } else if (midVal < target) {
                return search(nums, target, mid + 1, end, left);
            } else {
                return search(nums, target, start, mid - 1, left);
            }
        }

        return p;

    }

    public static void main(String[] args) {

        int[] a = {5, 7, 7, 8, 8, 10};
        FindFirstAndLastPositionOfElementInSortedArray sortedArray = new FindFirstAndLastPositionOfElementInSortedArray();

        System.out.println(Arrays.toString(sortedArray.searchRange(a, 8)));

    }

}
