package org.linuxlsx.algo.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutDuplicateWord {

    /**
     * 使用滑动窗口的模式来处理
     * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        int maxLength = 0;

        //这个字段表示当前子串的左界, 当前子串的右界就是当前遍历到的位置
        //当出现重复的字符后，需要移动左界到重复字符位置处，然后重新开始统计
        int latestRepeatIndex = -1;

        Map<Character, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {

            Character c = new Character(s.charAt(i));

            //注意这里的左界位置是和重复字符的位置相比，不是和当前遍历到的位置相比
            if (indexMap.containsKey(c) && latestRepeatIndex < indexMap.get(c)){
                latestRepeatIndex = indexMap.get(c);
            }

            if (i - latestRepeatIndex > maxLength) {
                maxLength = i - latestRepeatIndex;
            }

            indexMap.put(c, i);

        }

        return maxLength;
    }
}
