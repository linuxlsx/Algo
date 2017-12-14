package org.linuxlsx.algo.leetcode;

/**
 * Solution of  <a href="https://leetcode.com/problems/integer-to-roman/description/">Integer to Roman</a>
 *
 * @author linuxlsx
 * @date 2017/11/10
 */
public class IntegerToRoman {

    int[] step = {1000, 100, 10, 1};
    char[] symbol = {'M', 'C', 'X', 'I'};

    /**
     * 罗马计数法:  I(1)、V(5)、X(10)、L(50)、C(100)、D(500)、M(1000)
     * 1 : I, 2 : II, 3 : III, 4 : IV , 5 : V, 6 : VI, 7 : VII , 8 : VIII, 9 : IX , 10 : X
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();

        int tmp = num;
        int index = 0;
        while (tmp > 0) {

            if(tmp >= step[index]){

                //直接按位整除得到该位的数值
                int d = tmp / step[index];
                //直接获得该数值的罗马表示
                sb.append(convert(d, symbol[index]));
                tmp -= d * step[index];
            }else {
                index++;
            }

        }

        return sb.toString();
    }

    /**
     * 将数值和对应的罗马单位转为具体的罗马数值表示
     * 这里会涉及到 D L V 的转换
     * @param d
     * @param c
     * @return
     */
    private String convert(int d, char c) {

        switch (d) {
            case 1:
                return  "" + c ;
            case 2:
                return  "" + c + c;
            case 3:
                return  "" + c + c + c;
            case 4:
                return c + "" + getUpperChar(c);
            case 5:
                return getUpperChar(c) + "";
            case 6:
                return getUpperChar(c) + "" + c;
            case 7:
                return getUpperChar(c) + "" + c + "" + c;
            case 8:
                return getUpperChar(c) + "" + c + "" + c + "" + c;
            case 9:
                return c + "" + getUpperChar(getUpperChar(c));
        }

        return null;
    }

    /**
     * 获得指定罗马数字大一单位的罗马数字
     * @param c
     * @return
     */
    private char getUpperChar(char c) {

        switch (c) {
            case 'I':
                return 'V';
            case 'V':
                return 'X';
            case 'X':
                return 'L';
            case 'L':
                return 'C';
            case 'C':
                return 'D';
            case 'D':
                return 'M';
        }

        return '0';
    }

    /**
     * 以下是 左耳朵耗子的实现，通过将 900, 400 等这种需要特殊转换的数字也同其他标准的放到一起，
     * 这样能够达到代码简化的目的。 但是实际运行的时候 效率(102ms)稍稍不及自己实现的版本(92ms)。主要是循环次数会多一些
     *
     **/

    String[] symbolStr =   {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    int[]  value    =   {1000,900,500,400, 100, 90,  50, 40,  10, 9,   5,  4,   1};

    public String intToRomanV2(int num){

        StringBuilder sb = new StringBuilder();

        for (int i = 0; num != 0 ; i++){

            while (num >= value[i]){
                num -= value[i];
                sb.append(symbolStr[i]);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman toRoman = new IntegerToRoman();
        System.out.println(toRoman.intToRomanV2(214));
    }

}
