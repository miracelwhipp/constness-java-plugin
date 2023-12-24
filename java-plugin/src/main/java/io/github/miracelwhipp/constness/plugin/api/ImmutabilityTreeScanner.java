package io.github.miracelwhipp.constness.plugin.api;

import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.Tree;
import io.github.miracelwhipp.constness.plugin.utility.BooleanTreeScanner;

public final class ImmutabilityTreeScanner extends BooleanTreeScanner<ConstnessMarkApi> {

    private static final ImmutabilityTreeScanner INSTANCE = new ImmutabilityTreeScanner();

    private ImmutabilityTreeScanner() {
    }


    @Override
    public Boolean visitMemberSelect(MemberSelectTree node, ConstnessMarkApi api) {

        return RefersToConstContextTreeScanner.refersToConstContext(node.getExpression(), api);
    }

    @Override
    public Boolean visitIdentifier(IdentifierTree node, ConstnessMarkApi api) {

        return RefersToConstContextTreeScanner.refersToConstContext(api.getJavacApi().getParent(node), api);
    }

    @Override
    public Boolean visitMethodInvocation(MethodInvocationTree node, ConstnessMarkApi api) {

        return RefersToConstContextTreeScanner.refersToConstContext(node, api);
    }

    public static boolean isImmutable(Tree node, ConstnessMarkApi context) {

        return primitify(node.accept(INSTANCE, context));
    }
}
