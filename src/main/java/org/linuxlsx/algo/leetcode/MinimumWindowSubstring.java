package org.linuxlsx.algo.leetcode;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        if (s.length() < t.length()){
            return "";
        }

        if (s.equals(t)){
            return s;
        }


        int[] need = new int[128];
        //记录需要的字符的个数
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }

        //l是当前左边界，r是当前右边界，size记录窗口大小，count是需求的字符个数，start是最小覆盖串开始的index
        int l = 0, r = 0, size = Integer.MAX_VALUE, count = t.length(), start = 0;

        while (r < s.length()){
            char c = s.charAt(r);
            if (need[c] > 0) {
                count--;
            }

            need[c]--;

            if (count == 0){
                while (l < r && need[s.charAt(l)] < 0){
                    need[s.charAt(l)]++;
                    l++;
                }

                // 如果当前的这个窗口值比之前维护的窗口值更小，需要进行更新
                if (r - l + 1 < size){
                    size = r - l + 1;
                    start = l;


                }

                //l向右移动后窗口肯定不能满足了 重新开始循环
                need[s.charAt(l)]++;
                l++;
                count++;

            }


            r++;
        }

        return size == Integer.MAX_VALUE ? "" : s.substring(start, start + size);
    }

    public static void main(String[] args) {

        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();

        System.out.println(minimumWindowSubstring.minWindow("ADOBECODEBANC", "ABBC"));
    }

}
