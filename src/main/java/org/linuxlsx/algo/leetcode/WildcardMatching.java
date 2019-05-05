package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href='https://leetcode.com/problems/wildcard-matching/'>Wildcard Matching</a>
 *
 * @tag  动态规划
 * @author linuxlsx
 * @date 2019-04-22
 */
public class WildcardMatching {


    /**
     * 空间复杂度为 O(mn)的版本，使用了二维数组
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {

        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
        match[0][0] = true;

        // 初始化空串的匹配。
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') {
                match[0][i] = match[0][i - 1];
            }
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) != '*') {
                    match[i][j] = match[i - 1][j - 1] && (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1));
                } else {
                    match[i][j] = match[i][j - 1] || match[i - 1][j];
                }
            }
        }
        return match[s.length()][p.length()];
    }


    /**
     * 空间复杂度为 O(m)的版本，只使用了一维数组
     * @param s
     * @param p
     * @return
     */
    public boolean isMatchByDPV2(String s, String p) {
        int m = s.length(), n = p.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) == '*') {
                count++;
            }
        }
        if (count == 0 && m != n) {
            return false;
        } else if (n - count > m) {
            return false;
        }

        boolean[] match = new boolean[m + 1];
        match[0] = true;

        for (int i = 0; i < n; i++) {
            if (p.charAt(i) == '*') {
                for (int j = 0; j < m; j++) {
                    match[j + 1] = match[j] || match[j + 1];
                }
            } else {
                for (int j = m - 1; j >= 0; j--) {
                    match[j + 1] = (p.charAt(i) == '?' || p.charAt(i) == s.charAt(j)) && match[j];
                }
                match[0] = false;
            }
        }
        return match[m];
    }

    public static void main(String[] args) {

        WildcardMatching matching = new WildcardMatching();

        //System.out.println(matching.isMatch("aab", "*ab"));
        //System.out.println(matching.isMatch("aa", "a**"));
        System.out.println(matching.isMatch("aaaa", "***a"));

        //System.out.println(matching.isMatch("abefcdgiescdfimde", "ab*cd?i*de"));

        //System.out.println(matching.isMatch("cb", "?a"));
        //System.out.println(matching.isMatch("adceb", "*a*b"));
        //System.out.println(matching.isMatch("acdcb", "a*c?b"));

    }


}
