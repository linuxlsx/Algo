package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/">Remove Duplicates from Sorted Array</a>
 * @author linuxlsx
 * @date 2018/8/1
 */
public class RemoveDuplicatesFromSortedArray {

    /**
     * beats 100% java solutions, only 7ms
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {

        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return 1;
        }

        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[i-1]){
                nums[index] = nums[i];
                index++;
            }
        }

        return index;
    }

    public static void main(String[] args) {

        RemoveDuplicatesFromSortedArray removeDuplicatesFromSortedArray = new RemoveDuplicatesFromSortedArray();

        int[] array = {0,0,1,1,1,2,2,3,3,4,4};

        int len = removeDuplicatesFromSortedArray.removeDuplicates(array);

        System.out.println( "len -> " + len);
        for (int i = 0; i < len; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
