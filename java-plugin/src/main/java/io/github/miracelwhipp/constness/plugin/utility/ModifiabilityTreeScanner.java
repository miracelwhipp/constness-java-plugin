package io.github.miracelwhipp.constness.plugin.utility;

import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.Tree;

public final class ModifiabilityTreeScanner extends BooleanTreeScanner<CompilerTaskContext> {

    private static final ModifiabilityTreeScanner INSTANCE = new ModifiabilityTreeScanner();

    private ModifiabilityTreeScanner() {
    }


    @Override
    public Boolean visitMemberSelect(MemberSelectTree node, CompilerTaskContext context) {

        return RefersToConstContextTreeScanner.refersToConstContext(node.getExpression(), context);
    }

    @Override
    public Boolean visitIdentifier(IdentifierTree node, CompilerTaskContext context) {

        return RefersToConstContextTreeScanner.refersToConstContext(context.getParent(node), context);
    }

    @Override
    public Boolean visitMethodInvocation(MethodInvocationTree node, CompilerTaskContext context) {

        return RefersToConstContextTreeScanner.refersToConstContext(node, context);
    }

    public static boolean isModifiable(Tree node, CompilerTaskContext context) {

        return !primitify(node.accept(INSTANCE, context));
    }
}
