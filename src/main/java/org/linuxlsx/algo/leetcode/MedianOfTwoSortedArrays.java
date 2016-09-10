package org.linuxlsx.algo.leetcode;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *   Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 *
 * Created by rongruo.lsx on 16/9/9.
 */
public class MedianOfTwoSortedArrays {

    /**
     * 思路: 将两个排序的数组转化为一个大的排序数组, 然后在大的排序数组中取得中间数.
     *
     * <ul>
     *     <li>算法复杂度 O(m+n) 所以这个算法不满足条件的要求, 虽然能够通过leetcode 的判定</li>
     *     <li>控件复杂度 O(m+n) 需要额外的一个数组</li>
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

    public static void main(String[] args) {
        MedianOfTwoSortedArrays arrays = new MedianOfTwoSortedArrays();

        System.out.println(arrays.findMedianSortedArrays(new int[]{1,3}, new int[]{2,4}));
    }
}
