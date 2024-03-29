package org.linuxlsx.algo.leetcode;

/**
 * Solution of <a href="https://leetcode.com/problems/palindrome-number/">Palindrome Number</a>
 * Created by rongruo.lsx on 2016/10/11.
 */
public class PalindromeNumber {

    /**
     * 思路: 类似于判断回文字符串, 将数字转为字符串，然后从两端开始判断字符是否相同。
     * 注意 负数肯定不是回文整数
     *
     * <ul>
     *     <li>算法复杂度: O(n) 需要遍历一次</li>
     *     <li>空间复杂度: O(n) 需要借助额外存储</li>
     * </ul>
     * <p>
     * 实际这种解法并不满足题目要求, 题目要求是不能使用额外的空间
     *
     * @param x 需要判断的数字
     * @return 是否是回文数字
     */
    public boolean isPalindrome(int x) {

        if (x < 0) {
            return true;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(x);
        int i = 0;
        int j = sb.length() - 1;

        while (true) {
            if (sb.charAt(i) != sb.charAt(j)) {
                return false;
            }

            if (i > j) {
                return true;
            }
            i++;
            j--;
        }


    }

    /**
     * 思路: 因为不能使用额外存储，所以不能转换为String. 但是我们也是可以像判断字符串一样
     * 从首尾两端开始判断数字是否相同。
     *
     * <ul>
     *     <li>算法复杂度: O(n) 需要遍历一次</li>
     *     <li>空间复杂度: O(1) 只需要3个局部变量</li>
     * </ul>
     *
     * @param x
     * @return
     * @see <a href="https://github.com/haoel/leetcode/blob/master/algorithms/cpp/palindromeNumber">palindromeNumber</a>
     */
    boolean isPalindromeV2(int x) {
        if (x < 0) {
            return false;
        }

        int len = 1;
        for (len = 1; (x / len) >= 10; len *= 10) ;

        while (x != 0) {
            int left = x / len;
            int right = x % 10;

            if (left != right) {
                return false;
            }

            x = (x % len) / 10;
            len /= 100;
        }
        return true;
    }

    boolean isPalindromeV3(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }


        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }


        return x == revertedNumber || x == revertedNumber / 10;
    }


    public static void main(String[] args) {

        PalindromeNumber pn = new PalindromeNumber();

        System.out.println(pn.isPalindromeV2(121));
    }
}
