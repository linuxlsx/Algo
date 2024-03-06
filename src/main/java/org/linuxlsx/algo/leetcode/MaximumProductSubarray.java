package org.linuxlsx.algo.leetcode;

public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {

        if (nums == null) {
            return 0;
        }

        int[][] dp = new int[nums.length][2];

        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= 0) {
                dp[i][0] = Math.max(dp[i - 1][0] * nums[i], nums[i]);
                dp[i][1] = Math.min(dp[i - 1][1] * nums[i], nums[i]);
            } else {
                dp[i][0] = Math.max(dp[i - 1][1] * nums[i], nums[i]);
                dp[i][1] = Math.min(dp[i - 1][0] * nums[i], nums[i]);
            }

            max = Math.max(max, dp[i][0]);
        }


        return max;
    }

    public int maxProductV2(int[] nums) {

        if (nums == null) {
            return 0;
        }


        int res = nums[0];
        int max = nums[0];
        int min = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int tmpMax = max * nums[i];
            int tmpMin = min * nums[i];

            max = Math.max(Math.max(tmpMax, tmpMin), nums[i]);
            min = Math.min(Math.min(tmpMax, tmpMin), nums[i]);


            res = Math.max(max, res);
        }


        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1,-2,-9,-6};

        System.out.println(new MaximumProductSubarray().maxProductV2(nums));
    }
}
