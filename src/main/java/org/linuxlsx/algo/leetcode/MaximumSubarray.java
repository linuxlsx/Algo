package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href="https://leetcode.com/problems/maximum-subarray/">Maximum Subarray</a>
 *
 * @author linuxlsx
 * @date 2019-07-05
 */
public class MaximumSubarray {

    /**
     * To calculate sum(0,i), you have 2 choices: either adding sum(0,i-1) to a[i], or not.
     * If sum(0,i-1) is negative, adding it to a[i] will only make a smaller sum, so we add
     * only if it's non-negative.
     *
     * sum(0,i) = a[i] + (sum(0,i-1) < 0 ? 0 : sum(0,i-1))
     *
     * @see <a href='https://leetcode.com/problems/maximum-subarray/discuss/20193/DP-solution-and-some-thoughts '>DP solution & some thoughts</a>
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0], sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    /**
     * @see <a href='https://leetcode.com/problems/maximum-subarray/discuss/20211/Accepted-O(n)-solution-in-java'>Accepted O(n) solution in java</a>
     * @param nums
     * @return
     */
    public int maxSubArrayV2(int[] nums) {

        int maxSoFar = nums[0], maxEndingHere = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }


    public static void main(String[] args) {
        MaximumSubarray subarray = new MaximumSubarray();

        System.out.println(subarray.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
