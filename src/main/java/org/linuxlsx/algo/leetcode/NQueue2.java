package org.linuxlsx.algo.leetcode;

public class NQueue2 {

    public int count;

    public int totalNQueens(int n) {

        if (n <= 0) {
            return 0;
        }

        dfs(n, 0, 0, 0, 0);

        return count;
    }

    public void dfs(int n, int cur, int col, int pie, int na) {

        if (cur >= n) {
            count++;
            return;
        }

        int bits = (~(col | pie | na)) & ((1 << n) - 1);

        while (bits > 0) {

            int p = bits & -bits;
            dfs(n, cur + 1, (col | p),  (pie | p)  >> 1, (na | p) << 1);

            bits = bits & (bits - 1);
        }

    }

    public static void main(String[] args) {
        NQueue2 nQueue2 = new NQueue2();

        System.out.println(nQueue2.totalNQueens(4));
    }

}
