package org.linuxlsx.algo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Solution of <a href="https://leetcode.com/problems/roman-to-integer/description/">Roman to Integer</a>
 *
 * 知道罗马数字的结构会比较容易
 * @author linuxlsx
 * @date 2017/12/09
 */
public class RomanToInteger {

    Map<String, Integer> map = new HashMap<String, Integer>();

    {
        map.put("M", 1000);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("CD", 400);
        map.put("C", 100);
        map.put("XC", 90);
        map.put("L", 50);
        map.put("XL", 40);
        map.put("X", 10);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("IV", 4);
        map.put("I", 1);
    }

    /**
     * 每次都优先匹配两个字符，如果两个字符在数字表里面存在就使用这两个字符代表的值
     * 否则使用第一个字符所代表的值
     * @param s
     * @return
     */
    public int romanToInt(String s) {

        if (s.length()<=0) {
            return 0;
        }

        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            String ch = s.charAt(i) + "";
            if( (i+1) < s.length() ){
                ch += s.charAt(i+1);
            }
            if(map.containsKey(ch)){
                result += map.get(ch);
                i++;
            }else {
                result += map.get(s.charAt(i) + "");
            }
        }

        return result;
    }


    public int romanToIntV2(String s){

        if (s.length()<=0) {
            return 0;
        }
        int result = romanCharToInt(s.charAt(0));
        for (int i=1; i<s.length(); i++){
            int prev = romanCharToInt(s.charAt(i-1));
            int curr = romanCharToInt(s.charAt(i));
            //if left<right such as : IV(4), XL(40), IX(9) ...
            if (prev < curr) {
                result = result - prev + (curr-prev);
            }else{
                result += curr;
            }
        }
        return result;
    }


    int romanCharToInt(char ch){
        int d = 0;
        switch(ch){
            case 'I':
                d = 1;
                break;
            case 'V':
                d = 5;
                break;
            case 'X':
                d = 10;
                break;
            case 'L':
                d = 50;
                break;
            case 'C':
                d = 100;
                break;
            case 'D':
                d = 500;
                break;
            case 'M':
                d = 1000;
                break;
        }
        return d;
    }

    public static void main(String[] args) {
        RomanToInteger romanToInteger = new RomanToInteger();
        System.out.println(romanToInteger.romanToInt("CCXIV"));
    }
}
