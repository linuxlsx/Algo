package org.linuxlsx.algo.rete;

import java.util.List;

/**
 * @author linuxlsx
 * @date 2019-04-26
 */
public class Rete {

    public void alphaMemoryActivation(AlphaMemory alphaMemory, Wme wme){

        ItemInAlphaMemory item = new ItemInAlphaMemory();
        item.wme = wme;
        item.alphaMemory = alphaMemory;

        alphaMemory.items.addFirst(item);
        wme.alphaMemItems.addFirst(item);

        for (ReteNode child : alphaMemory.successors) {
            //TODO right-activation(child, wme)
        }

    }

    public void addWme(Wme wme){

        AlphaMemory alphaMemory = lookupInHashTable(wme.field, wme.operator, wme.value);
        alphaMemoryActivation(alphaMemory, wme);
    }

    public AlphaMemory lookupInHashTable(String field, String operator, String value){

        return new AlphaMemory();
    }


    public void betaMemoryLeftActivation(BetaMemory betaMemory, Token token, Wme wme){
        Token newToken = makeToken(null, token, wme);

        betaMemory.items.addFirst(newToken);

        for (ReteNode child : betaMemory.children) {
            //TODO  left-activation(child, newToken)
        }
    }

    public boolean performJoinTests(List<TestAtJoinNode> tests, Token token, Wme wme){

        for (TestAtJoinNode test : tests) {

        }

        return false;
    }

    public Token makeToken(ReteNode node, Token parent, Wme wme){

        Token token = new Token();
        token.parent = parent;
        token.wme = wme;
        token.node = node;

        if(wme != null){
            wme.tokens.addFirst(token);
        }

        return token;
    }
}
