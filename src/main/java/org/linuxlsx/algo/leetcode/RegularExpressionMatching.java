package org.linuxlsx.algo.leetcode;

/**
 *
 * Solution of <a href="https://leetcode.com/problems/regular-expression-matching/">regular-expression-matching</a>
 *
 * Created by linuxlsx on 2016/10/11.
 */
public class RegularExpressionMatching {



    public static void main(String[] args) {

        RegularExpressionMatching m = new RegularExpressionMatching();

        System.out.println(m.isMatch("aaa", "ab*a*c*a"));

    }

    /**
     * 思路: 这道题个人的思路是瀑布流是的直接匹配，针对 * 会做些合并之类的动作。但实际并不能解决问题。
     * 后续再网络上查找该问题的解题思路，主要有两种: 一种是递归，一种是动态规划。效率上递归一般在 400ms 左右，而动态规划
     * 只需要30ms, 性能相差巨大。
     *
     * 详细思路说明 <a href="https://segmentfault.com/a/1190000003904286">Regular Expression Matching</a>
     *
     * @param s     待匹配字符串
     * @param p     匹配串
     * @return      是否完全匹配
     */
    public boolean isMatch(String s, String p) {
        boolean[] match = new boolean[s.length() + 1];
        match[s.length()] = true;
        for(int i = p.length() - 1; i >=0; i--){
            if(p.charAt(i) == '*'){
                // 如果是星号，从后往前匹配
                for(int j = s.length() - 1; j >= 0; j--){
                    match[j] = match[j] || (match[j + 1] && (p.charAt(i - 1) == '.' || (p.charAt(i - 1) == s.charAt(j))));
                }
                // 记得把i多减一，因为星号是和其前面的字符配合使用的
                i--;
            } else {
                // 如果不是星号，从前往后匹配
                for(int j = 0; j < s.length(); j++){
                    match[j] = match[j + 1] && (p.charAt(i) == '.' || (p.charAt(i) == s.charAt(j)));
                }
                // 只要试过了pattern中最后一个字符，就要把match[s.length()]置为false
                match[s.length()] = false;
            }
        }
        return match[0];
    }
}
