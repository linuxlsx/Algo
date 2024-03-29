package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href='https://leetcode.com/problems/jump-game-ii/'>Jump Game II</a>
 *
 * @tag 贪心算法
 * @author linuxlsx
 * @date 2019-05-08
 */
public class JumpGameII {

    /**
     * O(n) 贪心算法
     * @param nums
     * @return
     */
    public int jump(int[] nums) {

        //跳动的次数
        int jumpCount = 0;
        //需要更新次数的位置
        int newJumpIndex = 0;
        //当前跳动周期内最远能够到达的位置
        int currentFarthestIndex = 0;
        for (int i = 0; i < nums.length - 1; i++) {

            //如果已经超过了边界，则直接返回。
            if(i + nums[i] >= nums.length - 1){
                return ++jumpCount;
            }

            //获取最远位置
            currentFarthestIndex = Math.max(currentFarthestIndex, i + nums[i]);

            //更新跳动次数
            if (i == newJumpIndex) {
                jumpCount++;
                newJumpIndex = currentFarthestIndex;
            }
        }
        return jumpCount;
    }


    public static void main(String[] args) {

        JumpGameII gameII = new JumpGameII();

        System.out.println(gameII.jump(new int[]{2,2,1,2,4}));
        //System.out.println(gameII.jump(new int[]{5, 6, 4, 4, 6, 9, 4, 4, 7, 4, 4, 8, 2, 6, 8, 1, 5, 9, 6, 5, 2, 7, 9, 7, 9, 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5}));
    }

}
