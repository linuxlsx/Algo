package org.linuxlsx.algo.rete;

/**
 * @author linuxlsx
 * @date 2019-04-26
 */
public class EqualEvalNode implements EvalNode{

    public String attr;
    public String value;

    public EqualEvalNode() {
    }

    public EqualEvalNode(String attr, String value) {
        this.attr = attr;
        this.value = value;
    }

    @Override
    public boolean eval(Factor factor) {
        return false;
    }
}
