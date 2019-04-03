package org.linuxlsx.algo.leetcode;

import java.util.*;

/**
 * Solution of  <a href='https://leetcode.com/problems/combination-sum/'>Combination Sum</a>
 * @author linuxlsx
 * @date 2019-04-03
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> lists = new ArrayList<>(8);

        Stack<Integer> stack = new Stack<>();
        stack.ensureCapacity(candidates.length);
        calc(candidates, target, 0,new Stack<>(), lists);

        return lists;
    }

    /**
     * 递归下降深度优先遍历。 算法复杂度 O((n^2 + n) / 2)
     * 最终结果是 6ms  beats 92%
     *
     * 分析了排在更前面的实现，发现主要的差异就是他们用List替代了Stack
     * 来保存中间状态，这样可以减少一个subList的操作。基本上可以到2ms
     *
     * @param candidates
     * @param target
     * @param start
     * @param stack         需要使用栈来保存中间状态。
     * @param lists
     */
    private void calc(int[] candidates, int target, int start, Stack<Integer> stack, List<List<Integer>> lists){

        for (int i = start; i < candidates.length; i++) {

            if(candidates[i] > target){
                continue;
            }

            stack.push(candidates[i]);
            if(candidates[i] == target){
                List<Integer> l = new ArrayList<>(stack.size());
                l.addAll(stack.subList(0, stack.size()));
                lists.add(l);
            }else if(candidates[i] < target) {
                calc(candidates, target - candidates[i], i ,stack , lists);
            }
            stack.pop();
        }
    }

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();

        int[] candidates = {2,3,5};

        List<List<Integer>> lists = combinationSum.combinationSum(candidates, 8);

        System.out.println(lists.toString());
    }
}
