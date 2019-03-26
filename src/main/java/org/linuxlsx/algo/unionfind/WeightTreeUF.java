package org.linuxlsx.algo.unionfind;

/**
 * User: niba
 * Date: 13-8-4
 * Time: 下午6:18
 */
public class WeightTreeUF extends TreeUF {

    private int[] sz;

    @Override
    public void init(int count) {
        super.init(count);

        sz = new int[count];
        for (int i = 0; i < count; i++) {
            sz[i] = 1;
        }

    }

    @Override
    public void union(int p, int q) {

        int i = find(p);
        int j = find(q);

        if(i == j){
            return;
        }

        if(sz[i] < sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        }else {
            id[j] = i;
            sz[i] += sz[j];
        }

        count--;

    }

    public static void main(String[] args) {
        WeightTreeUF wtf = new WeightTreeUF();

        UFUtil.run(wtf, DATA_PATH + "largeUF.txt");
    }
}
