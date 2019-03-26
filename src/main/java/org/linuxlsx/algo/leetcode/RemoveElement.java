package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href="https://leetcode.com/problems/remove-element/description/">Remove Element</a>
 * @author linuxlsx
 * @date 2018/8/1
 */
public class RemoveElement {

    /**
     * beats 100% java solutions, only 4ms
     */
    public int removeElement(int[] nums, int val) {

        if(nums == null || nums.length == 0){
            return 0;
        }

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != val){
                nums[index] = nums[i];
                index++;
            }
        }

        return index;
    }

    public static void main(String[] args) {

        RemoveElement removeElement = new RemoveElement();

        int[] array = {2};

        int len = removeElement.removeElement(array, 2);

        System.out.println( "len -> " + len);
        for (int i = 0; i < len; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
