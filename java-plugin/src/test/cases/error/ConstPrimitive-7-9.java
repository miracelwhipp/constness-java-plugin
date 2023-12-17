import io.github.miracelwhipp.constness.annotation.Const;

class ConstPrimitive {

// tag::const-primitive[]
    @Const
    int value = 7; // compile error
// end::const-primitive[]
}

