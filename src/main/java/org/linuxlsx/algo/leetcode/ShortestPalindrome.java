package org.linuxlsx.algo.leetcode;

public class ShortestPalindrome {

    public String shortestPalindrome(String s) {

        StringBuilder sb = new StringBuilder(s);
        String rs = sb.reverse().toString();

        int len = s.length();

        for (int i = len; i >= 0; i--) {

            System.out.println( s.substring(0, i) + " -> " + rs.substring(len - i));
            if (s.substring(0, i).equals(rs.substring(len - i))){
                return rs.substring(0, len - i) + s;
            }
        }

        return "";
    }

    public static void main(String[] args) {
        ShortestPalindrome shortestPalindrome = new ShortestPalindrome();

        System.out.println(shortestPalindrome.shortestPalindrome("aa"));
    }
}
