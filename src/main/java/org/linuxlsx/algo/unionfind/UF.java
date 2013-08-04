package org.linuxlsx.algo.unionfind;

/**
 * union-find 算法
 * User: niba
 * Date: 13-7-23
 * Time: 上午12:05
 */
public class UF extends AbstractUF{

    /**
     * 给对象p, q 添加一个联通分量
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if (pId == qId) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }

        count--;
    }

    /**
     * 返回 对象所在的节点
     * @param p
     * @return
     */
    public int find(int p) {
        return id[p];
    }

    public static void main(String[] args) {
        UF uf = new UF();
        UFUtil.run(uf, DATA_PATH + "mediumUF.txt");
    }
}
