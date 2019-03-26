package org.linuxlsx.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Solution of <a href="https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/">Letter Combinations of a Phone Number</a>
 *
 * @author linuxlsx
 * @date 2017/12/14
 */
public class LetterCombinationsOfPhoneNumber {

    private Map<String, List<String>> numberLetterMap = new HashMap<String, List<String> >();

    {
        numberLetterMap.put("1", Arrays.asList(new String[]{}));
        numberLetterMap.put("2", Arrays.asList(new String[]{"a", "b", "c"}));
        numberLetterMap.put("3", Arrays.asList(new String[]{"d", "e", "f"}));
        numberLetterMap.put("4", Arrays.asList(new String[]{"g", "h", "i"}));
        numberLetterMap.put("5", Arrays.asList(new String[]{"j", "k", "l"}));
        numberLetterMap.put("6", Arrays.asList(new String[]{"m", "n", "o"}));
        numberLetterMap.put("7", Arrays.asList(new String[]{"p", "q", "r", "s"}));
        numberLetterMap.put("8", Arrays.asList(new String[]{"t", "u", "v"}));
        numberLetterMap.put("9", Arrays.asList(new String[]{"w", "x", "y", "z"}));
        numberLetterMap.put("0", Arrays.asList(new String[]{}));

    }

    /**
     * 该问题可以认为多个字符数组做笛卡尔积。具体java 的笛卡尔积实现说明可参见
     * <a href="https://www.maxith.com/2017/08/30/cartesian-product/">java 算法系列 - 笛卡尔积算法</a>
     *
     * 实现中采用递归来实现 DFS 的遍历。
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {

        if (digits == null || digits.length() == 0){
            return new ArrayList<String>();
        }

        if(digits.length() == 1){
            return  numberLetterMap.get(digits);
        }

        List<List<String>> lists = new ArrayList<List<String>>();

        for (int i = 0; i < digits.length(); i++) {
            List<String> stringList = numberLetterMap.get("" + digits.charAt(i));
            lists.add(stringList);
        }

        String[][] result = cartesianProduct(lists);

        List<String> resultList = new ArrayList<String>();

        for (String[] strings : result) {
            String tmp = "";
            for (String string : strings) {
                tmp += string;
            }

            resultList.add(tmp);
        }


        return resultList;
    }

    private static String[][] cartesianProduct(List<List<String>> args) {
        int total = 1;
        //字符组的索引
        int counterIndex = args.size() - 1;
        //每个字符组内部的缩影
        int[] counter = new int[args.size()];
        for (int i = 0; i < args.size(); i++) {
            total *= args.get(i).size();
            counter[i] = 0;
        }

        String[][] result = new String[total][args.size()];
        for (int i = 0; i < total; i++) {
            for (int j = 0; j < args.size(); j++) {
                result[i][j] = args.get(j).get(counter[j]);
            }
            counterIndex = handle(counter, counterIndex, args);
        }
        return result;
    }

    private static int handle(int[] counter, int counterIndex, List<List<String>> args) {
        counter[counterIndex]++;
        if (counter[counterIndex] >= args.get(counterIndex).size()) {
            counter[counterIndex] = 0;
            counterIndex--;
            if (counterIndex >= 0) {
                handle(counter, counterIndex, args);
            }
            counterIndex = args.size() - 1;
        }
        return counterIndex;
    }


    //==============================解法二=====================================

    /**
     * 递归的解法是 DFS式的遍历，可以通过使用队列来完成 BFS 式的遍历
     * @param digits
     * @return
     */
    public List<String> letterCombinationsV2(String digits) {

        LinkedList<String> ans = new LinkedList<String>();

        if (digits.length()==0){
            return ans;
        }

        String[] mapping = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));

            //最终的结果字符串的长度是和 输入数字的长度是一致的，
            //所以这个地方可以通过判断长度的方式来检查是否需要进行合并
            while(ans.peek().length()==i && !"".equals(mapping[x])){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray()) {
                    ans.add(t + s);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber letterCombinationsOfPhoneNumber = new LetterCombinationsOfPhoneNumber();
        letterCombinationsOfPhoneNumber.letterCombinationsV2("21");
    }
}
