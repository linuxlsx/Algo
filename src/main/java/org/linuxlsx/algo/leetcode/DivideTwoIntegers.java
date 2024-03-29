package org.linuxlsx.algo.leetcode;

/**
 * @author linuxlsx
 * @date 2018/8/16
 */
public class DivideTwoIntegers {

    /**
     * https://leetcode.cn/problems/divide-two-integers/solutions/1043142/tong-ge-lai-shua-ti-la-bei-zeng-cheng-fa-6qbg/
     * 使用倍增乘法解决
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {

        if (dividend == Integer.MIN_VALUE && divisor == -1){
            return Integer.MAX_VALUE;
        }

        int sign = -1;
        if ((dividend > 0 && divisor >0) || (dividend < 0 && divisor < 0)){
            sign = 1;
        }

        dividend = dividend > 0 ? -dividend : dividend;
        divisor = divisor > 0 ? -divisor : divisor;

        int ans = 0;

        while (dividend <= divisor){
            int tmp = divisor, count = 1;
            // 防止溢出, 所以用减法
            while (tmp >= dividend - tmp) {
                tmp += tmp;
                count += count;
            }

            dividend -= tmp;
            ans += count;
        }

        return sign < 0 ? -ans : ans;
    }
}
