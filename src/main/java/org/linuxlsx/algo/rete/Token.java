package org.linuxlsx.algo.rete;

import java.util.List;

/**
 * @author linuxlsx
 * @date 2019-04-28
 */
public class Token {

    public Token parent;

    public Wme wme;

    public ReteNode node;

    public List<Token> children;

    List joinResults;

    List nccResults;

    public Token owner;
}
