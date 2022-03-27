package org.linuxlsx.algo.leetcode;

public class RemoveDuplicatesII {

    public int removeDuplicates(int[] nums) {

        if(nums.length <= 2){
            return nums.length;
        }

        int slow = 2;
        int fast = 2;

        while (fast < nums.length){

            if(nums[slow - 2] != nums[fast]){
                nums[slow] = nums[fast];
                slow++;
            }

            fast++;
        }

        return slow;

    }
}
