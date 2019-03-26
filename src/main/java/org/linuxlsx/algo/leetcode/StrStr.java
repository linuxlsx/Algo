package org.linuxlsx.algo.leetcode;

/**
 * @author rongruo.lsx
 * @date 2018/8/16
 */
public class StrStr {

    /**
     * 使用暴力模式来匹配字符串。时间复杂度为 O(n*m)。除此之外还可以使用 KMP, BM,
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {

        if(haystack == null || needle == null || "".equals(needle)){
            return 0;
        }

        for (int i = 0; i <= (haystack.length() - needle.length()); i++) {

            int j;
            for (j = 0; j < needle.length(); j++) {
                if(haystack.charAt(i+j) != needle.charAt(j)){
                    break;
                }
            }

            if(j == needle.length()){
                return i;
            }

        }

        return -1;
    }

    public static void main(String[] args) {

        StrStr strStr = new StrStr();

        System.out.println(strStr.strStr("", "a"));
    }
}
