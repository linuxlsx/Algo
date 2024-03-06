package org.linuxlsx.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solution of <a href="https://leetcode.com/problems/3sum/description/">3Sum</a>
 *
 * @author linuxlsx
 * @date 2017/12/09
 */
public class ThreeNumberSumToZero {

    /**
     * 首先对数组进行排序，这个是关键一步。
     * 然后对每个数字都计算与其他两个数字的和，利用数组已经排序的特性，可以从左右两个方向进行运算
     *
     * <ul>
     * <li>时间复杂度 O(n log(n))  + O( n^2 )</li>
     * </ul>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int lo = i + 1;
                int hi = nums.length - 1;
                int sum = 0 - nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        //将符合要求的结果保存下来
                        res.add(Arrays.asList(nums[lo], nums[i], nums[hi]));
                        //过滤掉重复的数据
                        while (lo < hi && nums[lo] == nums[lo + 1]) {
                            lo++;
                        }
                        while (lo < hi && nums[hi] == nums[hi - 1]) {
                            hi--;
                        }
                        //这个地方需要额外的再增减一次
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < sum) {
                        //如果和比目标小，那么左索引自增
                        lo++;
                    } else {
                        //如果和比目标大，那么右索引自增
                        hi--;
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum2(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }

        for (int i = 0; i < nums.length; i++) {
            int lo = i + 1;
            int hi = nums.length - 1;
            int sum = 0 - nums[i];
            while (lo < hi) {
                int tmp = nums[lo] + nums[hi];
                if (tmp == sum) {
                    res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    while (lo < hi && nums[lo] == nums[lo + 1]) {
                        lo++;
                    }
                    while (lo < hi && nums[hi] == nums[hi - 1]) {
                        hi--;
                    }

                    lo++;
                    hi--;
                }else if (tmp < sum) {
                    lo++;
                }else {
                    hi--;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        ThreeNumberSumToZero threeNumberSumToZero = new ThreeNumberSumToZero();

        threeNumberSumToZero.threeSum(
                new int[]{0, 14, -7, 2, 7, 11, -9, 11, -12, 6, -10, -8, 9, -3, 7, -6, 3, 4, 14, -10, -8, 5, -1, 6, 12, 9,
                        12, -11, -15, -5, 5, 0, -6, 13, 9, 9, 10, 7, 5, 13, -2, 11, -10, -15, -15, 4, -14, -4, -15, 7, -7, -15,
                        -3, 8, -2, -13, -6, -5, -9, -14, 5, 12, 1, 6, 2, -12, -8, -11, 10, 13, -13, -14, 1, 14, 8, 1, -4, 9, 4,
                        -12, -6, 13, 10, 6, 6, -7, 8, 7, 3, 7, 8, -15, -4, -14, -1, 13, -11, 6, -12, -15, 4, 12, 8, -10, 4, 1,
                        -1, 7, -13, -12, 10, -4, 8, 6, 10, -1, 6, -5, 13, -13, 9, 6, -13, -10});
    }
}
