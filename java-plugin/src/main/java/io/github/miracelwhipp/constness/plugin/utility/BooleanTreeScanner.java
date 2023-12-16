package io.github.miracelwhipp.constness.plugin.utility;

import com.sun.source.tree.Tree;
import com.sun.source.util.TreeScanner;

public class BooleanTreeScanner<ParameterType> extends TreeScanner<Boolean, ParameterType> {

    protected static boolean primitify(Boolean bool) {

        return bool != null && bool;
    }

    @Override
    public Boolean scan(Iterable<? extends Tree> nodes, ParameterType parameter) {

        if (nodes == null) return false;

        for (Tree node : nodes) {

            // evaluate lazily
            if (primitify(scan(node, parameter))) return true;
        }

        return false;
    }

    @Override
    public Boolean reduce(Boolean r1, Boolean r2) {

        boolean firstResult = primitify(r1);

        if (firstResult) return true;

        return primitify(r2);
    }
}
