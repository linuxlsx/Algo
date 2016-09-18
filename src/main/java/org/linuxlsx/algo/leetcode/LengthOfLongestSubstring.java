package org.linuxlsx.algo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Solution of  <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">Longest Substring Without Repeating Characters</a>
 *
 * Created by linuxlsx on 16/9/9.
 */
public class LengthOfLongestSubstring {

    /**
     * 思路: 通过两个循环遍历字符串,循环过程中记录最大的子串的长度。
     *
     * <ul>
     *     <li>算法复杂度 O(n^2) 当字符串没有重复字符时 最好: O(n) 当字符串全为相同字符时</li>
     *     <li>控件复杂度 O(n) n 为字符串中不重复的字符数量</li>
     * </ul>
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        int maxLength = 0;

        Map<Character, String> map = new HashMap<Character, String>();

        int length = s.length();

        for (int i = 0; i < length; i++) {
            int len = 0;
            int tmp = i;
            for (int j = i; j < length; j++) {
                Character key = new Character(s.charAt(j));
                if(map.containsKey(key)){
                    break;
                }else {
                    map.put(key, "1");
                }
            }

            len = map.size();

            if(len > maxLength){
                maxLength = len;
            }

            //当最大长度已经大于剩余的子串长度时,可认为已经找到最大值,这样可以将最坏情况下的O(n^2) 提升为 O(n)
            //实际判定中 比不加该判断减少了 20% 左右的时间
            //加之前 106ms , 加之后  85
            if(maxLength >= (length - tmp)){
                break;
            }


            map.clear();
        }

        return maxLength;
    }

    /**
     * 思路来源: 左耳朵耗子 <a href="https://github.com/haoel/leetcode/blob/master/algorithms/cpp/longestSubstringWithoutRepeatingCharacters/longestSubstringWithoutRepeatingCharacters.cpp">longestSubstringWithoutRepeatingCharacters</a>
     * <br />
     * 思路: 决定子串长度的主要因素是重复字符出现的位置. 最近一次出现重复字符的位置与当前位置的差就是子串的长度
     *
     * <ul>
     *     <li>算法复杂度 O(n) 一次遍历即可  实测 20ms 以内</li>
     *     <li>空间复杂度 O(n) n 为字符串中不重复的字符数量</li>
     * </ul>
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringV2(String s){
        int maxLength = 0;

        Map<Character, Integer> map = new HashMap<Character, Integer>();

        int length = s.length();
        int lastRepeatIndex = -1;

        for (int i = 0; i < length; i++) {

            Character ch = new Character(s.charAt(i));

            if(map.get(ch) != null && lastRepeatIndex < map.get(ch)){
                lastRepeatIndex = map.get(ch);
            }

            if(i - lastRepeatIndex > maxLength){
                maxLength = i - lastRepeatIndex;
            }

            map.put(ch, i);
        }

        return maxLength;
    }

    public static void main(String[] args) {

        LengthOfLongestSubstring s = new LengthOfLongestSubstring();

        System.out.println(s.lengthOfLongestSubstringV2("c"));

    }
}
