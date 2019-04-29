package org.linuxlsx.algo.rete;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author linuxlsx
 * @date 2019-04-26
 */
public class AlphaMemory {

    /**
     * item in alpha memory
     */
    public LinkedList<ItemInAlphaMemory> items = new LinkedList<>();

    public LinkedList<ReteNode> successors = new LinkedList<>();

    /**
     * 表名有多少 join node 或者 negative node 使用到这个 AlphaMemory
     */
    public int referenceCount;
}
