package org.linuxlsx.algo.leetcode;

public class ClimbingStairs {

    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int one_before = 2;
        int two_before = 1;
        int all_ways = 0;
        for (int i = 2; i < n; i++) {
            all_ways = one_before + two_before;
            two_before = one_before;
            one_before = all_ways;
        }

        return all_ways;
    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();

        System.out.println(climbingStairs.climbStairs(4));
    }
}

