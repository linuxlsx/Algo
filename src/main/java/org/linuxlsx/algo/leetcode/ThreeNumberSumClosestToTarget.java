package org.linuxlsx.algo.leetcode;

import java.util.Arrays;

/**
 * Solution of <a href="https://leetcode.com/problems/3sum-closest/description/">3Sum Closest</a>
 * @author linuxlsx
 * @date 2017/12/14
 */
public class ThreeNumberSumClosestToTarget {

    /**
     * 基本解法与  org.linuxlsx.algo.leetcode.ThreeNumberSumToZero 的解决方案基本一致。
     * 主要思想也是先对数组进行排序，然后根据三个数字的和与目标的大小关系来决定变化的索引。
     * <br />
     * 然后在计算 "最近" 的时候需要考虑到 target 为负数的情况
     *
     * <ul>
     *     <li>时间复杂度 排序 O(n log(n)) + 计算 O（ n^2 ) n 为数组的长度 </li>
     * </ul>
     * @param nums      目标数组
     * @param target    计算最近值的目标
     * @return  与 target 相差最近的 和
     */
    public int threeSumClosest(int[] nums, int target) {

        if(nums.length < 3){
            return 0;
        }

        int closest = Integer.MIN_VALUE;
        int closestSum = Integer.MIN_VALUE;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int lo = i + 1;
                int hi = nums.length - 1;
                while (lo < hi) {

                    int sum = nums[i] + nums[lo] + nums[hi];

                    System.out.println( nums[i] + " + " + nums[lo] + " + " + nums[hi] + " = " + sum );

                    int minus = 0;
                    if(target >= 0){
                        minus = Math.abs(target - sum);
                    }else {
                        minus = Math.abs(sum - target);
                    }

                    if(closest == Integer.MIN_VALUE || closest > minus){
                        closest = minus;
                        closestSum = sum;
                    }

                    if (sum < target) {
                        //如果和比目标小，那么左索引自增
                        lo++;
                    } else {
                        //如果和比目标大，那么右索引自增
                        hi--;
                    }
                }
            }
        }
        return closestSum;
    }

    public static void main(String[] args) {
        ThreeNumberSumClosestToTarget threeNumberSumClosestToTarget = new ThreeNumberSumClosestToTarget();

        //System.out.println(threeNumberSumClosestToTarget.threeSumClosest(new int[]{1, 1, 1, 0}, -100));
        //System.out.println(threeNumberSumClosestToTarget.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        //System.out.println(threeNumberSumClosestToTarget.threeSumClosest(new int[]{ -3, 0, 1, 2 }, 1));
        System.out.println(threeNumberSumClosestToTarget.threeSumClosest(new int[]{ -1, 0, 1, 1, 55 }, 3));
    }
}
