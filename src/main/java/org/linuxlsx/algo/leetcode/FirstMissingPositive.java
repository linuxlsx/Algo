package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href='https://leetcode.com/problems/first-missing-positive'>First Missing Positive</a>
 * @author linuxlsx
 * @date 2019-04-11
 */
public class FirstMissingPositive {

    /**
     * 解题方式来自  http://stackoverflow.com/questions/1586858/find-the-smallest-integer-not-in-a-list
     * 主要思路通过数组的下标来标记数组中已经出现过的最小正整数。对于 < 1 和 > N (数组长度)的整数都可以丢弃。
     * 算法复杂度为 O(3N)，额外空间为1
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {

        int p;
        //第一个O(N)
        for (int i = 0; i < nums.length; i++) {
            p = nums[i];

            //为什么这里也只会最多执行O(N)次
            //因为在条件中会判断 nums[p - 1] != p，然后在循环体里面会设置 nums[p - 1] = p
            //那么每执行一次赋值，就会减少一个可赋值的位置。
            //就可以保证至多执行N次
            while (p > 0 && p < nums.length && nums[p - 1] != p) {
                nums[i] = nums[p - 1];
                nums[p - 1] = p;
                p = nums[i];

                System.out.println("swap");
            }
        }

        //第三个O(N)
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i]) {
                return i + 1;
            }
        }

        //只有数组是 [1,2,3,4] 这种情况才会走到这里
        return nums.length + 1;
    }

    public static void main(String[] args) {

        FirstMissingPositive fmp = new FirstMissingPositive();

        //System.out.println(fmp.firstMissingPositive(new int[]{7,8,9,11,12}));
       System.out.println(fmp.firstMissingPositive(new int[]{1,2,3,4}));
        //System.out.println(fmp.firstMissingPositive(new int[]{7,6,5,4,3,2,1}));
    }
}
