package org.linuxlsx.algo.leetcode;

import java.util.Arrays;

/**
 * Solution of  <a href="https://leetcode.com/problems/longest-common-prefix/description/">Longest Common Prefix</a>
 *
 * @author linuxlsx
 * @date 2017/12/09
 */
public class LongestCommonPrefix {

    /**
     * 选取第一个字符串为基准，与剩余的字符串逐一比较。如果出现不匹配或者索引大于字符串长度时终止
     * <ul>
     *     <li>时间复杂度 O(n * n) n为最长列表的长度</li>
     *     <li>空间复杂度 O(4) 只需要4个变量</li>
     * </ul>
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return "";
        }

        if(strs.length == 1){
            return strs[0];
        }

        String prefix = "";
        String first = strs[0];

        int index = 0;
        boolean test = true;
        while (test){

            if(first.length() <= index){
                break;
            }

            char a = first.charAt(index);

            for (int i = 1; i < strs.length; i++){
                if(strs[i].length() <= index){
                    test = false;
                    break;
                }

                if(strs[i].charAt(index) != a){
                    test = false;
                    break;
                }
            }

            if(test){
                prefix += a;
                index++;
            }
        }

        return prefix;
    }

    /**
     * 首先将字符串进行排序，然后选取最长和最短的作比较就可以了
     *
     * <ul>
     *     <li>时间复杂度 O(nlg(n) + m) n 为数组的长度, m 为数组中最短的字符串长度</li>
     *     <li>空间复杂度 O(n/2 + m + k) n 为数组的长度, m 为数组中最短的字符串长度, k 为数组中最长的字符串的长度</li>
     * </ul>
     * @param strs
     * @return
     */
    public String longestCommonPrefixV2(String[] strs) {
        StringBuilder result = new StringBuilder();

        if (strs != null && strs.length > 0) {

            Arrays.sort(strs);

            char[] a = strs[0].toCharArray();
            char[] b = strs[strs.length - 1].toCharArray();

            for (int i = 0; i < a.length; i++) {
                if (b.length > i && b[i] == a[i]) {
                    result.append(b[i]);
                } else {
                    return result.toString();
                }
            }
            return result.toString();
        }

        return result.toString();

    }

    public static void main(String[] args) {

        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();

        String[] strs = {"", ""};

        System.out.println(longestCommonPrefix.longestCommonPrefix(strs));
    }
}
