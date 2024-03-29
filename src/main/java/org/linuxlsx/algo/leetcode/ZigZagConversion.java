package org.linuxlsx.algo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Solution of <a href="https://leetcode.com/problems/zigzag-conversion/">ZigZag Conversion</a>
 * <p>
 * Created by linuxlsx on 16/9/18.
 */
public class ZigZagConversion {

    /**
     * 思路:  根据题目提示画出一个 numRows=5 的结构图, 可以将该问题简化一个坐标 (x, y) 的问题, 最终结果就是根据纵坐标的顺序
     * 输出所有的字符。
     * <pre>
     *       P(0,0)            P(4,0)
     *       P(0,1)     P(3,1) P(4,1)
     *       P(0,2)   P(2,2)   P(4,2)
     *       P(0,3) P(1,3)     P(4,3)
     *       P(0,4)            P(4,4)
     *      </pre>
     * <p>
     * 所以只要遍历字符串的时候将字符放到纵坐标对应的字符串的末尾, 最后按照纵坐标的顺序输出字符串。需要注意的是纵坐标自增和
     * 自减的位置。根据上面的结构可以看出: 当纵坐标 y == 0 和 y == (numRows - 1) 的时候需要变化纵坐标的自增方式。
     *
     * <ul>
     *     <li>
     *         算法复杂度: O(N+2M), N 为字符串的长度, M为列数
     *     </li>
     *     <li>
     *         空间复杂度: O(N), N 为字符串的长度
     *     </li>
     * </ul>
     *
     * @param s       需要转换的字符串
     * @param numRows 列数
     * @return 转换后的字符串
     * @see <a href="https://github.com/haoel/leetcode/blob/master/algorithms/cpp/zigZagConversion/zigZagConversion.cpp">zigZagConversion</a>
     */
    public String convert(String s, int numRows) {

        //特例
        if (numRows <= 1 || s.length() <= numRows) {
            return s;
        }

        Map<Integer, StringBuilder> map = new HashMap<Integer, StringBuilder>();
        for (int i = 0; i <= numRows; i++) {
            map.put(i, new StringBuilder());
        }

        int row = 0;
        boolean down = false;
        for (int i = 0; i < s.length(); i++) {
            if (row == (numRows - 1)) {
                down = !down;
            } else if (row == 0) {
                down = !down;
            }
            map.get(row).append(s.charAt(i));
            if (down) {
                row++;
            } else {
                row--;
            }

        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= numRows; i++) {
            sb.append(map.get(i).toString());
        }

        return sb.toString();
    }

    public String convert2(String s, int numRows) {

        if (numRows <= 2) {
            return s;
        }


        String[] res = new String[numRows];
        for (int i = 0; i < numRows; i++) {
            res[i] = "";
        }

        int flag = -1;
        int index = 0;

        for (int i = 0; i < s.length(); i++) {
            res[index] = res[index] + s.charAt(i);

            if (index == 0 || index == numRows - 1) {
                flag = -flag;
            }

            index += flag;
        }

        StringBuilder sb = new StringBuilder();
        for (String ss : res){
            sb.append(ss);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        ZigZagConversion zzc = new ZigZagConversion();

        System.out.println(zzc.convert2("AB", 1));
    }
}
