package org.linuxlsx.algo.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author linuxlsx
 * @date 2019-04-22
 */
public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        List<Automata> list = new ArrayList<>();
        for (int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == '*'){
                Automata automata = new Automata();
                automata.p = p.charAt(i);
                StringBuilder sb = new StringBuilder();
                //过滤掉重复的 '*'
                while (i < p.length() - 1){
                    if(p.charAt(i+1) == '*'){
                        i++;
                    }else {
                        break;
                    }
                }

                while (i < p.length() - 1){
                    if(p.charAt(i + 1) != '?' && p.charAt(i + 1) != '*'){
                        sb.append(p.charAt(++i));
                    }else {
                        break;
                    }
                }
                automata.postFix = sb.toString();
                list.add(automata);
            }else {
                Automata automata = new Automata();
                automata.p = p.charAt(i);
                list.add(automata);
            }
        }

        int i = 0;

        Iterator<Automata> iterator = list.iterator();

        while (iterator.hasNext()){

            Automata automata = iterator.next();

            // 到这里说明 s 被消费完了 p 还有剩余
            if(i == s.length()){
                break;
            }

            if(automata.p == '*'){
                //如果是 '*' 则使用贪婪模式，先将所有剩余字符消费掉，然后在从后往前匹配 postFix,
                //找到右的位置即为 '*' 的匹配结束位置

                //如果 '*' 后面没有跟 postFix : 有可能是p的最后一个字符，也有可能是 * 后面紧跟了 ?
                //因为连续的 ** 会被合并成 *
                if("".equals(automata.postFix)){
                    if(iterator.hasNext()){

                    }else {
                        //这个说明 * 是 p 的最后一个字符
                        i = s.length();
                    }
                }else {
                    int j = s.substring(i).lastIndexOf(automata.postFix);
                    if(j < 0){
                        return false;
                    }

                    //设置新的待匹配的位置
                    i = i + j + automata.postFix.length();
                }

            }else if(automata.p == '?'){
                i++;
            }else {
                if(automata.p != s.charAt(i)){
                    return false;
                }
                i++;
            }


            iterator.remove();
        }

        if(i != s.length()){
            return false;
        }

        if(list.isEmpty()){
            return true;
        }else {
            for (Automata automata : list) {
                if(automata.p != '*' || !"".equals(automata.postFix)){
                    return false;
                }
            }
        }

        return true;
    }


    public static class Automata{

        public char p;

        public String postFix;
    }

    public static void main(String[] args) {

        WildcardMatching matching = new WildcardMatching();

        //System.out.println(matching.isMatch("aab", "*ab"));
        System.out.println(matching.isMatch("aa", "a**"));
        System.out.println(matching.isMatch("aaaa", "***a"));

        //System.out.println(matching.isMatch("abefcdgiescdfimde", "ab*cd?i*de"));

        //System.out.println(matching.isMatch("cb", "?a"));
        System.out.println(matching.isMatch("adceb", "*a*b"));
        //System.out.println(matching.isMatch("acdcb", "a*c?b"));

    }


}
