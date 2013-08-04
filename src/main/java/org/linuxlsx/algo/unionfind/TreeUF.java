package org.linuxlsx.algo.unionfind;

/**
 * User: niba
 * Date: 13-8-4
 * Time: 下午5:35
 */
public class TreeUF extends AbstractUF {

    @Override
    public int find(int p) {

        while (p != id[p]){
            p = id[p];
        }

        return p;
    }

    @Override
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);

        if(i == j){
            return;
        }

        id[i] = j;
        count--;
    }

    public static void main(String[] args) {
        TreeUF treeUF = new TreeUF();

        UFUtil.run(treeUF, DATA_PATH + "largeUF.txt");
    }
}
