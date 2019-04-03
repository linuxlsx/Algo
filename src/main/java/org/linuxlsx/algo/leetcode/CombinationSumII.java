package org.linuxlsx.algo.leetcode;

import java.util.*;

/**
 * Solution of <a href='https://leetcode.com/problems/combination-sum-ii/'>Combination Sum II</a>
 * @author linuxlsx
 * @date 2019-04-03
 */
public class CombinationSumII {


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> lists = new ArrayList<>(8);

        Arrays.sort(candidates);
        calc(candidates, target, 0,new ArrayList<>(candidates.length), lists);

        return lists;
    }

    /**
     * 递归下降深度优先遍历。 算法复杂度 O((n^2 + n) / 2)
     * 2 ms  beats 100%
     * @param candidates
     * @param target
     * @param start
     * @param stack         需要使用栈来保存中间状态。
     * @param lists
     */
    private void calc(int[] candidates, int target, int start, List<Integer> stack, List<List<Integer>> lists){

        if (start >= candidates.length ) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            //这里是解题的关键。为什么是 i > start 的时候来判断重复的元素
            //因为当 i == start 的时候是整个深度遍历的第一次。所有的元素都是
            //在递归的层级上是不重复的。只有当 i > start 以后才是在同一个层级
            //做遍历，所以需要判重
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            if(candidates[i] > target){
                break;
            }

            stack.add(candidates[i]);
            if(candidates[i] == target){
                lists.add(new ArrayList<>(stack));
            }else if(candidates[i] < target) {
                calc(candidates, target - candidates[i], i+1 ,stack , lists);
            }

            stack.remove(stack.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSumII sumII = new CombinationSumII();

        System.out.println(sumII.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
        System.out.println(sumII.combinationSum2(new int[]{2,5,2,1,2}, 5));
    }
}
