package org.linuxlsx.algo.leetcode;

import java.util.Collections;

public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {

        if (prices == null || prices.length < 2) {
            return 0;
        }


        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++) {

            if (minPrice > prices[i]) {
                minPrice = prices[i];
            } else if (maxProfit < prices[i] - minPrice) {
                maxProfit = prices[i] - minPrice;
            }

        }

        return maxProfit;
    }

    public int maxProfitDp(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int maxProfit = 0;

        int[][] dp = new int[prices.length][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;

        for (int i = 1; i < prices.length; i++) {

            dp[i][0] = dp[i - 1][0];
            //dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = dp[i - 1][1] + prices[i];

            maxProfit = Math.max(maxProfit, Math.max(dp[i][0], Math.max(dp[i][1], dp[i][2])));

        }

        return maxProfit;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{7, 1, 5, 3, 6, 4};

        System.out.println(new BestTimeToBuyAndSellStock().maxProfitDp(nums));
    }

}
