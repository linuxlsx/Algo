package org.linuxlsx.algo.rete;

import java.util.List;

/**
 * @author linuxlsx
 * @date 2019-04-28
 */
public class ReteNode {

    public NodeType type;
    public List<ReteNode> children;
    public ReteNode parent;
}
