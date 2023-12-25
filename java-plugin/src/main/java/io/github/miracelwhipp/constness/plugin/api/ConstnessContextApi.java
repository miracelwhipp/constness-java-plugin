package io.github.miracelwhipp.constness.plugin.api;

import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.Tree;

import javax.lang.model.element.Element;

public class ConstnessContextApi {

    private final ConstnessMarkApi markApi;

    public ConstnessContextApi(ConstnessMarkApi markApi) {
        this.markApi = markApi;
    }

    public boolean isConstInContext(Tree tree) {

        return RefersToConstContextTreeScanner.refersToConstContext(tree, markApi);
    }

    public boolean isImmutableInContext(Tree tree) {

        return ImmutabilityTreeScanner.isImmutable(tree, markApi);
    }

    public boolean invocationIsInConstContext(MethodInvocationTree node) {

        // TODO: check all scopes using ScopesTreeScanner
        Element containingElement = ContainingElementTreeScanner.getContainingMethod(node, markApi.getJavacApi());

        if (containingElement != null &&
                (markApi.markedAsConst(containingElement) || markApi.markedAsPreservesConst(containingElement))
        ) {

            return true;
        }

        if (isConstInContext(node.getMethodSelect())) {

            return true;
        }

        return false;
    }



}
