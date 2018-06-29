package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href="https://leetcode.com/problems/valid-parentheses/description/">Valid Parentheses</a>
 *
 * @author linuxlsx
 * @date 2017/12/09
 */
public class ValidParentheses {

    public boolean isValid(String s) {

        if(s.length() == 0){
            return true;
        }

        if (s.length() == 1){
            return false;
        }

        char[] stacks = new char[s.length()];
        int index = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c){
                case '(':
                case '[':
                case '{' :
                    stacks[index++] = c;
                    break;
                case ')':
                    if(index == 0 || stacks[index - 1] != '('){
                        return false;
                    }
                    stacks[--index] = '0';
                    break;
                case ']':
                    if(index == 0 || stacks[index - 1] != '['){
                        return false;
                    }
                    stacks[--index] = '0';
                    break;
                case '}':
                    if(index == 0 || stacks[index - 1] != '{'){
                        return false;
                    }
                    stacks[--index] = '0';
                    break;
            }
        }

        return stacks[0] == '0';
    }

    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();

        System.out.println(validParentheses.isValid("()[]{}"));
    }
}
