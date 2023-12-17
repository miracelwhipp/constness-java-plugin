import io.github.miracelwhipp.constness.annotation.Const;


class CastConstAway {

    static {
// tag::cast-const-away[]
        @Const
        Object object = new Object();
        Object nonConst;
        nonConst = object; // compile error
// end::cast-const-away[]
    }
}