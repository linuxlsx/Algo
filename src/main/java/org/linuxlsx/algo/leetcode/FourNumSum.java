package org.linuxlsx.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

/**
 * Solution of <a href="https://leetcode.com/problems/4sum/description/">4Sum</a>
 *
 * @author linuxlsx
 * @date 2017/12/15
 */
public class FourNumSum {

    /**
     * 首先将数组排序。
     * 将 4Sum 的问题分解为 数字 arr[i] 与 子数组 arr[i+1]...arr[n] 3Sum 的方式
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {

        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                for (int j = i + 1; j < nums.length - 2; j++) {

                    if (j == (i + 1) || nums[j] != nums[j - 1]) {

                        int lo = j + 1;
                        int hi = nums.length - 1;
                        int sum = target - nums[i] - nums[j];
                        while (lo < hi) {
                            if (nums[lo] + nums[hi] == sum) {
                                //将符合要求的结果保存下来
                                res.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
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
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FourNumSum fourNumSum = new FourNumSum();
        List<List<Integer>> lists = fourNumSum.fourSum(new int[]{1000000000,1000000000,1000000000,1000000000}, -294967296);

        //2147483647
        //1000000000
        //-2147483648
        //-1294967296
        //-294967296


        //List<List<Integer>> lists = fourNumSum.fourSum(new int[] {-4,-1,-1,0,1,2,}, -1);
        Gson gson = new Gson();

        for (List<Integer> list : lists) {
            System.out.print(gson.toJson(list) + " ");
        }
    }
}
