package org.linuxlsx.algo.leetcode;

/**
 *
 * Solution of <a href="https://leetcode.com/problems/median-of-two-sorted-arrays/">Median of Two Sorted Arrays</a>
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *   Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 *
 * Created by linuxlsx on 16/9/9.
 */
public class MedianOfTwoSortedArrays {

    /**
     * 思路: 将两个排序的数组转化为一个大的排序数组, 然后在大的排序数组中取得中间数.
     *
     * <ul>
     *     <li>算法复杂度 O(m+n) 所以这个算法不满足条件的要求, 虽然能够通过leetcode 的判定</li>
     *     <li>空间复杂度 O(m+n) 需要额外的一个数组</li>
     * </ul>
     *
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        double median = 0;

        int[] bigArray = new int[nums1.length + nums2.length];

        int p1 = 0;
        int p2 = 0;
        int p3 = 0;

        for (int i = 0; i < bigArray.length; i++) {

            if(p1 < nums1.length && p2 < nums2.length){
                if(nums1[p1] < nums2[p2]){
                    bigArray[p3] = nums1[p1];
                    p1++;
                }else {
                    bigArray[p3] = nums2[p2];
                    p2++;
                }
            }else if(p1 < nums1.length){
                bigArray[p3] = nums1[p1];
                p1++;
            }else {
                bigArray[p3] = nums2[p2];
                p2++;
            }

            p3++;
        }

        if(bigArray.length % 2 == 0){
            int m = bigArray.length / 2;
            median = ((double) bigArray[m-1] + (double) bigArray[m])/2.0;

        }else {
            median = (double)bigArray[bigArray.length / 2];
        }


        return median;
    }

    public double findMedianSortedArraysV2(int[] nums1, int[] nums2) {

        if(nums1.length > nums2.length){
            int[] tmp = nums2;
            nums2 = nums1;
            nums1 = tmp;
        }

        int x = nums1.length;
        int y = nums2.length;

        int low = 0;
        int high = x;
        while (low <= high){

            int pX = (low + high) / 2;
            int pY = (x + y + 1) / 2 - pX;

            int maxLeftX = pX == 0 ? Integer.MIN_VALUE : nums1[pX - 1];
            int minRightX = pX == x ? Integer.MAX_VALUE : nums1[pX];

            int maxLeftY = pY == 0 ? Integer.MIN_VALUE : nums2[pY - 1];
            int minRightY = pY == y ? Integer.MAX_VALUE : nums2[pY];

            if(maxLeftX <= minRightY && maxLeftY <= minRightX ){

                if((x + y) % 2 == 0){
                    return ((double) Math.max(maxLeftX, maxLeftY) + (double) Math.min(minRightX, minRightY)) / 2;
                }else {
                    return (double) Math.max(maxLeftX, maxLeftY);
                }
            }else if(maxLeftX > minRightY){
                high = pX - 1;
            }else {
                low = pX + 1;
            }
        }

        throw new IllegalArgumentException();
    }

    /**
     * 查找两个排序数组的中位数，其实可以转化成找到前 k 小的元素位置
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArraysV3(int[] nums1, int[] nums2) {

        // 第一步 将长度小的放到前面
        if(nums1.length > nums2.length){
            int[] tmp = nums2;
            nums2 = nums1;
            nums1 = tmp;
        }

        // 确定 k 的大小
        int k = (nums1.length + nums2.length + 1) / 2;
        int left = 0;
        int right = nums1.length;

        // m1 是数组1 中位数
        int m1 = 0;
        // m2 是数组2 中位数
        int m2 = 0;

        while (left < right) {

            m1 = left + (right - left) / 2;
            m2 = k - m1;

            // 如果数组1的中位数小于数组2的中位数-1
            if (nums1[m1] < nums2[m2 - 1]) {
                left = m1 + 1;
            }else {
                right = m1;
            }
        }

        m1 = left;
        m2 = k - m1;

        int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1-1] , m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);

        if ((nums1.length + nums2.length) % 2== 1){
            return (double) c1;
        }

        int c2 = Math.min(m1 >= nums1.length ? Integer.MAX_VALUE : nums1[m1], m2 >= nums2.length ? Integer.MAX_VALUE : nums2[m2]);


        return ((double) (c1 + c2)) / 2;
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays arrays = new MedianOfTwoSortedArrays();

        System.out.println(arrays.findMedianSortedArraysV3(new int[]{1,3}, new int[]{2,4,5,6,7,8}));
    }
}
