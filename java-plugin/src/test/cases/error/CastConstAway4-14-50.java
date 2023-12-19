import io.github.miracelwhipp.constness.annotation.Const;
import io.github.miracelwhipp.constness.annotation.ConstValue;


class CastConstAway {

// tag::cast-const-away[]
    static @ConstValue Object newObject() {

        return new Object();
    }

    static {
        Object nonConst = CastConstAway.newObject(); // compile error
// end::cast-const-away[]
    }
}