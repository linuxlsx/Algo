package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href='https://leetcode.com/problems/search-insert-position/'>Search Insert Position</a>
 *
 * @tag  BinarySearch
 *
 * @author linuxlsx
 * @date 2019-03-21
 */
public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {

        int start = 0;
        int end = nums.length - 1;

        while (start <= end){

            int mid = (start + end) >>> 1;
            int midVal = nums[mid];
            if(midVal == target){
                return mid;
            }

            //当用二分法查到只剩一个元素时，就可以判断应该插入的位置了
            //目标大则插入到后面，目标小，则当前位置就是目标的位置。
            if(start == end){
                if(nums[start] > target){
                    return start;
                }else {
                    return start+1;
                }
            }

            if (target < midVal) {
                end = (mid - 1) < start ? start : (mid -1);
            }else {
                start = (mid + 1) > end ? end : (mid + 1);
            }

        }


        return -1;
    }

    public static void main(String[] args) {

        SearchInsertPosition position = new SearchInsertPosition();

        System.out.println(position.searchInsert(new int[]{1,3}, 2));
        System.out.println(position.searchInsert(new int[]{3,5,7,9,10}, 8));
        System.out.println(position.searchInsert(new int[]{1,3,5,6}, 5));
        System.out.println(position.searchInsert(new int[]{1,3,5,6}, 2));
        System.out.println(position.searchInsert(new int[]{1,3,5,6}, 7));
        System.out.println(position.searchInsert(new int[]{1,3,5,6}, 0));
    }
}
