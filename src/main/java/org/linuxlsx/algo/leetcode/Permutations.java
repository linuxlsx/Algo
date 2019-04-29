package org.linuxlsx.algo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution of <a href="https://leetcode.com/problems/permutations/">Permutations</a>
 *
 * @author linuxlsx
 * @date 2019-04-28
 */
public class Permutations {

    /**
     * 使用回溯法来解决问题
     * @tag 回溯
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        backtrack(nums, lists, new ArrayList<>());

        return lists;
    }

    private void backtrack(int[] nums, List<List<Integer>> lists, List<Integer> stack) {
        if (stack.size() == nums.length) {
            lists.add(new ArrayList<>(stack));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (stack.contains(nums[i])){
                    continue;
                }
                stack.add(nums[i]);
                backtrack(nums, lists, stack);
                stack.remove(stack.size() - 1);
            }
        }
    }

    public static void main(String[] args) {

        Permutations permutations = new Permutations();
        System.out.println(permutations.permute(new int[]{1, 2, 3, 4}));

    }
}
