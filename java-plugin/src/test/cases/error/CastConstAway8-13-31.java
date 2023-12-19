import io.github.miracelwhipp.constness.annotation.Const;
import io.github.miracelwhipp.constness.annotation.ConstValue;


class CastConstAway {

// tag::cast-const-away[]

    @Const
    Object object = new Object();

    {
        Object nonConst = this.object; // compile error
// end::cast-const-away[]
    }
}