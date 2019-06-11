package org.linuxlsx.algo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Solution of <a href="https://leetcode.com/problems/permutations-ii/">PermutationsII</a>
 *
 * @author linuxlsx
 * @date 2019-06-11
 */
public class PermutationsII {

    /**
     * 使用回溯法来解决问题。在解法中利用一个Map来记录每个数字出现的次数，每使用一次计数减一，
     * 当计数归零时说明该数字已经被用完了。
     *
     * 解法来自 : <a href='https://leetcode.com/problems/permutations-ii/discuss/18602/9-line-python-solution-with-1-line-to-handle-duplication-beat-99-of-others-%3A-)'>9-line python solution with 1 line to handle duplication, beat 99% of others :-)</a>
     *
     * @tag 回溯
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();

        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            Integer c = counter.get(num);
            if (c == null) {
                counter.put(num, 1);
            }else {
                counter.put(num, c + 1);
            }
        }
        backtrack(nums, lists, new ArrayList<>(), counter);

        return lists;
    }

    private void backtrack(int[] nums, List<List<Integer>> lists, List<Integer> stack, Map<Integer, Integer> counter) {
        if (stack.size() == nums.length) {
            lists.add(new ArrayList<>(stack));
        } else {

            //注意这里遍历的是计数的Map 而不是原来的数组
            for (Integer i : counter.keySet()) {

                //只对计数大于0的数字做后续的操作
                if(counter.get(i) > 0){
                    counter.put(i, counter.get(i) - 1);
                    stack.add(i);
                    backtrack(nums,lists, stack, counter);
                    stack.remove(stack.size() - 1);
                    counter.put(i, counter.get(i) + 1);
                }
            }
        }
    }

    public static void main(String[] args) {

        PermutationsII permutations = new PermutationsII();
        System.out.println(permutations.permuteUnique(new int[]{1, 1, 2}));
        System.out.println(permutations.permuteUnique(new int[]{1}));

    }
}
