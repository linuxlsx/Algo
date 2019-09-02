package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href='https://leetcode.com/problems/jump-game/'>Jump Game</a>
 *
 * @author linuxlsx
 * @date 2019-09-01
 */
public class JumpGame {

    /**
     * 使用贪心算法
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {

        int nextMaxIndex = 0;

        for (int i = 0; i <= nums.length - 1 && i <= nextMaxIndex; i++) {

            if(nextMaxIndex < (i + nums[i])){
                nextMaxIndex = (i + nums[i]);
            }

            nextMaxIndex = Math.max(nextMaxIndex, i + nums[i]);
        }

        return nextMaxIndex >= nums.length - 1;
    }

    public boolean canJumpV2(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    public static void main(String[] args) {

        int[] a = {2,5,0,0};
        int[] b = {0};
        int[] c = {0,2,5};
        int[] d = {1,1,2,2,0,1,1};

        JumpGame jumpGame = new JumpGame();

        System.out.println(jumpGame.canJump(a));
        System.out.println(jumpGame.canJump(b));
        System.out.println(jumpGame.canJump(c));
        System.out.println(jumpGame.canJump(d));
    }
}
