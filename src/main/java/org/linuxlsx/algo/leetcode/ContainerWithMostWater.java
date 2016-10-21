package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href="https://leetcode.com/problems/container-with-most-water/">Container With Most Water</a>
 * Created by linuxlsx on 2016/10/21.
 */
public class ContainerWithMostWater {


    /**
     * 思路: <p> 了解题目意图后可以简化为求面积的问题。对于线(i, ai) (j, aj)  j > i, 那么这两条先与X轴的面积为:
     *      <strong>(j-i)*min(ai, aj)</strong> 。 所以简单的办法是计算出所有线两两组合后的面积，再取其中的最大值。
     *      但是这个算法的复杂度就变成了 O(n^2) 了，显然不符合预期。 </p>
     *
     *      <p>
     *          计算面积的两个变量中 j, i 我们是可以指定位置的，我们将分别指定为数组的头和尾，从两头向中间逼近的方式计算
     *          面积的最大值，因为面积最大要不就是头尾两条线或者在两条线内部。 为了找最大面积，保留 max(ai, aj) 不变，
     *          另外一个进行变化(++ 或者 --)
     *      </p>
     *
     *      <ul>
     *          <li>算法复杂度 : O(n) </li>
     *          <li>空间复杂度: O(1) 只需要三个变量</li>
     *      </ul>
     * @param height
     * @return      最大的面积值
     */
    public int maxArea(int[] height) {

        if(height.length < 2){
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int max = 0;

        while (left < right){

            int area = (right-left) * (height[left] <= height[right] ? height[left] : height[right]);
            if(area > max){
                max = area;
            }

            //这个地方可以稍微做一个小优化, 如果步进以后比前一个还小，那就没必要计算了
            //可以减少一些不必要的乘法运算
            if(height[left] <= height[right]){
                do {
                    left++;
                } while (left < right && height[left-1] >= height[left]);
            }else {
                do {
                    right--;
                } while (right > left && height[right+1] >= height[right]);
            }
        }

        return max;
    }

    public static void main(String[] args) {

        int[] arr = {1,2,2};

        ContainerWithMostWater withMostWater = new ContainerWithMostWater();

        System.out.println(withMostWater.maxArea(arr));

    }
}
