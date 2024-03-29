package org.linuxlsx.algo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution of <a href="https://leetcode.com/problems/generate-parentheses/description/">Generate Parentheses</a>
 *
 * @author linuxlsx
 * @date 2018/7/2
 */
public class GenerateParentheses {

    /**
     * 这题自己只想到一个蛮力的办法，先枚举出所有的场景，然后来判断是否合法。不过这种方式肯定不是最好的方式。
     * 所有这里的解决方式是使用这个问题的标准解决方案 ^_^ 。主要是使用回溯的方式来做实现。
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {

        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);

        return list;
    }

    public void backtrack(List<String> ans, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max) {
            backtrack(ans, cur + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrack(ans, cur + ")", open, close + 1, max);
        }
    }

    public static void main(String[] args) {

        GenerateParentheses generateParentheses = new GenerateParentheses();
        List<String> list = generateParentheses.generateParenthesis(3);

        System.out.println(list);
    }

}
