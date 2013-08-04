package org.linuxlsx.algo.unionfind;

/**
 * User: niba
 * Date: 13-8-4
 * Time: 下午5:27
 */
public abstract class AbstractUF implements UFInterface {

    protected int[] id;
    protected int count;

    protected AbstractUF() {
    }

    public void init(int count){
        this.count = count;
        id = new int[count];

        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}
