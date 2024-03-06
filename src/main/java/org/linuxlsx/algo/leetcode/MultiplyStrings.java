package org.linuxlsx.algo.leetcode;


import java.util.HashMap;

public class MultiplyStrings {

    public static void main(String[] args) {


    }

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String ans = "0";



        return ans;
    }

    public static String addStrings(String a, String b) {

        int i = a.length() - 1;
        int j = b.length() - 1;
        int add = 0;


        StringBuilder sb = new StringBuilder();

        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? a.charAt(i) - '0' : 0;
            int y = j >= 0 ? b.charAt(j) - '0' : 0;
            int result = x + y + add;
            sb.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }

        sb.reverse();
        return sb.toString();
    }
}
