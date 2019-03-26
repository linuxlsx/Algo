package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href="https://leetcode.com/problems/string-to-integer-atoi/">String To Integer</a>
 * Created by linuxlsx on 2016/9/26.
 */
public class StringToInteger {

    /**
     * 思路: 主要是考虑到各种特殊的情况。根据题目的提示有以下几种特殊情况:
     *  <ul>
     *      <li>1. 正,负的处理. 只能是第一个字符</li>
     *      <li>2. 忽略空格</li>
     *      <li>3. 如果有溢出, 则返回Integer.MAX_VALUE 或者 Integer.MIN_VALUE, 根据正负号决定</li>
     *      <li>4. 如果在字符串中间碰到非数字字符, 则返回该字符之前的整数</li>
     *  </ul>
     *
     *  满足以上条件后, 实际的转换操作同 Integer.valueOf(String str)
     *
     * @param str   输入字符串
     * @return      转换后的整数
     * @see <a href="https://github.com/haoel/leetcode/blob/master/algorithms/cpp/stringToIntegerAtoi/stringToIntegerAtoi.cpp">stringToIntegerAtoi</a>
     */
    public int myAtoi(String str) {

        String strAfterTrim = str.trim();

        if (strAfterTrim.length() == 0) {
            return 0;
        }

        int tmpValue = 0;
        int start = 0;
        int limit = -Integer.MAX_VALUE;
        boolean negative = false;
        char c = strAfterTrim.charAt(0);
        if (c < '0') {
            if (c == '-') {
                negative = true;
                limit = Integer.MIN_VALUE;
            } else if (c != '+') {
                return 0;
            }
            start++;
        }

        int multiMin = limit / 10;

        while (start < strAfterTrim.length()) {

            char ch = strAfterTrim.charAt(start);

            if (ch >= '0' && ch <= '9') {

                int digit = Character.digit(ch, 10);

                if (tmpValue < multiMin) {
                    tmpValue = limit;
                    break;
                }

                tmpValue *= 10;
                if (tmpValue < limit + digit) {
                    tmpValue = limit;
                    break;
                }

                tmpValue -= digit;

            } else {
                break;
            }


            start++;
        }

        if (negative) {
            return tmpValue;
        } else {
            return -tmpValue;
        }


    }

    public static void main(String[] args) {

        StringToInteger sti = new StringToInteger();

        System.out.println(sti.myAtoi("   -115579378e25"));

    }
}
