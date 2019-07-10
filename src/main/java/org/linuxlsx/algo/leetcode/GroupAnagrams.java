package org.linuxlsx.algo.leetcode;

import java.util.*;

/**
 * Solution of <a href="https://leetcode.com/problems/group-anagrams/">Group Anagrams</a>
 * @author linuxlsx
 * @date 2019-07-03
 */
public class GroupAnagrams {

    /**
     * 简单的解法就是对于每个字符串做排序，根据排序后的字符串做分组key
     * 算法复杂度 N*K*log(K)
     * 实际执行时间：43ms beats 11% java online submission
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> results = null;

        Map<String, List<String>> groupResult = new HashMap<>();

        for (String str : strs) {

            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);

            groupResult.computeIfAbsent(key, s -> new ArrayList<>()).add(str);
        }

        results = new ArrayList<>(groupResult.size());

        results.addAll(groupResult.values());

        return results;
    }

    /**
     * 字符串满足 Anagrams 当且仅当所有字符出现的次数一样。所以可以通过统计字符串中每个字符出现的个数来判断
     * 算法复杂度 N * K
     * 实际执行时间：15ms beats 30% java online submission
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagramsV2(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }


    public static void main(String[] args) {

        String[] str = {"cab","tin","pew","duh","may","ill","buy","bar","max","doc"};

        GroupAnagrams groupAnagrams = new GroupAnagrams();

        List<List<String>> lists = groupAnagrams.groupAnagramsV2(str);

        System.out.println(lists);

    }
}
