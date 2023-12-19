import io.github.miracelwhipp.constness.annotation.Const;
import io.github.miracelwhipp.constness.annotation.ConstValue;


class CastConstAway {

// tag::cast-const-away[]

    @Const
    static Object object = new Object();

    static {
        Object nonConst = object; // compile error
// end::cast-const-away[]
    }
}