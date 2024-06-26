package org.linuxlsx.algo.leetcode;

public class PowerXn {


    public double myPow(double x, int n) {
        return myPow(x, (long) n);
    }

    public double myPow(double x, long n) {

        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            return  1 / (myPow(x, -n));
        }

        if (n % 2 == 1) {
            return x * myPow(x, n - 1);
        }

        return myPow(x * x, n / 2);
    }

    public static void main(String[] args) {
        System.out.println(new PowerXn().myPow(2.0, -2147483648));
    }
}