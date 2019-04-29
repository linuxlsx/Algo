package org.linuxlsx.algo.rete;

import java.util.LinkedList;
import java.util.List;

/**
 * @author linuxlsx
 * @date 2019-04-26
 */
public class Wme {

    public String field;

    public String operator;

    public String value;

    LinkedList<ItemInAlphaMemory> alphaMemItems = new LinkedList<>();

    LinkedList<Token> tokens = new LinkedList<>();

    LinkedList negativeJoinResult;

}
