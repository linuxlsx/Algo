package org.linuxlsx.algo.unionfind;

/**
 * User: niba
 * Date: 13-8-4
 * Time: 下午5:17
 */
public interface UFInterface {

    public static final String DATA_PATH = "D:/workspace/github/Algo/algs4-data/";

    public int find(int p);

    public boolean connected(int p, int q);

    public void union(int p, int q);
}
