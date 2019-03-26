package org.linuxlsx.algo.leetcode;

/**
 *
 * Solution of <a href="https://leetcode.com/problems/reverse-integer/">Reverse Integer</a>
 *
 * Created by linuxlsx on 16/9/23.
 */
public class ReverseInteger {

    /**
     * 思路: 将 int 转化为 String, 然后 reverse, 再通过与 Integer.MAX_VALUE 与 Integer.MIN_VALUE 比较
     * 判断 reverse 后的数值是否超过限制, 如果超过溢出的话 返回 0
     *
     * <ul>
     *     <li>算法复杂度 : O(n) 需要将字符串反转</li>
     * </ul>
     * @param x     输入的整数
     * @return      反转后的整数
     * @see <a href="https://github.com/haoel/leetcode/blob/master/algorithms/cpp/reverseInteger/reverseInteger.cpp">reverseInteger</a>
     */
    public int reverse(int x) {

        StringBuilder sb = new StringBuilder();
        sb.append(x);

        boolean isNegative = false;
        if(sb.charAt(0) == '-'){
            isNegative = true;
            sb.deleteCharAt(0);
        }

        sb.reverse();

        long tmp = Long.parseLong(sb.toString());

        if(tmp <= Integer.MAX_VALUE && tmp > Integer.MIN_VALUE){
            if(isNegative){
                sb.insert(0, '-');
            }
            return Integer.valueOf(sb.toString());
        }else if(tmp == Integer.MIN_VALUE){
            if(isNegative){
                sb.insert(0, '-');
                return Integer.valueOf(sb.toString());
            }
        }

        return 0;
    }


    public int reverseV2(int x) {
        int y=0;
        int n=0;
        while( x != 0){
            n = x%10;
            //Checking the over/underflow.
            //Actually, it should be y>(INT_MAX-n)/10, but n/10 is 0, so omit it.
            if (y > Integer.MAX_VALUE/10 || y < Integer.MIN_VALUE/10){
                return 0;
            }
            y = y*10 + n;
            x /= 10;
        }
        return y;
    }

    public static void main(String[] args) {

        ReverseInteger ri = new ReverseInteger();

        System.out.println(ri.reverseV2(1));

    }
}
