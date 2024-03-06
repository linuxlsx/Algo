package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href='https://leetcode.com/problems/trapping-rain-water/'>Trapping Rain Water</a>
 *
 * @author linuxlsx
 * @date 2019-04-15
 */
public class TrappingRainWater {

    /**
     * 实现思路:
     * 1. 从左向右寻找这样一个模式: |       |
     * |...|...|
     * 2. 当右边界大于左边界时即可计算整体的蓄水量。
     * 3. 接下来把右边界当做左边界，继续循环。
     * 4. 有能出现左边界是最大值，那么需要左边界右边第二大的数当做右边界，计算蓄水量
     * 5. 把右边界当左边界继续循环
     * <p>
     * 时间复杂度 最好情况下O(N)，最坏情况下是 O(N^2)，当给定数组是一个倒序的数组的时候，每次需要进行回溯。
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {

        if (height.length < 3) {
            return 0;
        }

        int total = 0;
        int left = 0;
        int maxLessThanLeftIndex = 0;
        int maxLessThanLeftValue = 0;
        int solid = 0;

        while (left < height.length - 2) {

            boolean cal = false;
            for (int i = left + 1; i < height.length; i++) {
                if (height[left] >= height[i]) {
                    solid += height[i];

                    //计算第二大的数，以便后续回溯使用
                    if (maxLessThanLeftValue <= height[i]) {
                        maxLessThanLeftIndex = i;
                        maxLessThanLeftValue = height[i];
                    }
                } else {
                    //在这里说明要计算蓄水了
                    total += (Math.min(height[left], height[i]) * (i - left - 1) - solid);
                    left = i;
                    cal = true;
                    break;
                }
            }

            if (!cal) {
                //到这里说明左边界是最大的值，需要使用第二大的数来做右边界。
                solid = 0;
                for (int i = left + 1; i < maxLessThanLeftIndex; i++) {
                    solid += height[i];
                }
                total += (Math.min(height[left], height[maxLessThanLeftIndex]) * (maxLessThanLeftIndex - left - 1) - solid);

                left = maxLessThanLeftIndex;
            }

            maxLessThanLeftIndex = 0;
            maxLessThanLeftValue = 0;
            solid = 0;
        }


        return total;
    }

    /**
     * 双指针版本。 O(N)
     * @param height
     * @return
     */
    int trap2Points(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        int left_max = 0, right_max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= left_max) {
                    left_max = height[left];
                } else {
                    ans += (left_max - height[left]);
                }
                ++left;
            } else {
                if (height[right] >= right_max) {
                    right_max = height[right];
                } else {
                    ans += (right_max - height[right]);
                }
                --right;
            }
        }
        return ans;
    }

    int trap2PointsByDp(int[] height){
        int sum = 0;
        int max_left = 0;
        int[] max_right = new int[height.length];

        for(int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i+1], height[i+1]);
        }

        for (int i = 1; i < height.length - 1; i++){
            max_left = Math.max(max_left, height[i-1]);
            int min = Math.min(max_left, max_right[i]);

            if (min > height[i]) {
                sum += (min - height[i]);
            }

        }

        return sum;

    }


    public static void main(String[] args) {
        TrappingRainWater rainWater = new TrappingRainWater();
        System.out.println(rainWater.trap(new int[]{6, 4, 2, 0, 3, 2, 0, 3, 1, 4, 5, 3, 2, 7, 5, 3, 0, 1, 2, 1, 3, 4, 6, 8, 1, 3}));
    }
}
