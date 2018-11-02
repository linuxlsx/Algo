package org.linuxlsx.algo.leetcode;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Solution of  <a href="https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/">Substring with Concatenation of All Words</a>
 *
 * @author linuxlsx
 * @date 2018/8/27
 */
public class SubstringWithConcatenationOfAllWords {

    /**
     * Solution From https://leetcode.com/problems/substring-with-concatenation-of-all-words/discuss/124256/clean-code-in-java(beats-100)
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> indices = new ArrayList<>();
        if (words.length == 0) {
            return indices;
        }

        //将每个词放到一个Map 中，并统计这个词出现的次数
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        //用一个单词的长度作为后面循环的步长以及外层循环的次数
        int wordLength = words[0].length();
        // 这个整个需要匹配的子串的长度
        int window = words.length * wordLength;

        for (int i = 0; i < wordLength; i++) {
            //move a word's length each time
            for (int j = i; j + window <= s.length(); j = j + wordLength) {
                //get the subStr
                String subStr = s.substring(j, j + window);
                Map<String, Integer> map = new HashMap<>();
                //start from the last word
                for (int k = words.length - 1; k >= 0 ; k--) {
                    //get the word from subStr
                    String word = subStr.substring(k * wordLength, (k + 1)* wordLength);
                    int count = map.getOrDefault(word, 0) + 1;
                    //if the num of the word is greater than wordMap's, move (k * wordLength) and break
                    if (count > wordMap.getOrDefault(word, 0)) {
                        j = j + k * wordLength;
                        break;
                    } else if (k == 0){
                        indices.add(j);
                    } else {
                        map.put(word, count);
                    }
                }
            }

        }
        return indices;


    }

    public static void main(String[] args) {

        SubstringWithConcatenationOfAllWords allWords = new SubstringWithConcatenationOfAllWords();
        List<Integer> list = allWords.findSubstring("barfoobaffoobar", new String[]{"foo", "bar"});

        System.out.println(new Gson().toJson(list));

    }
}
