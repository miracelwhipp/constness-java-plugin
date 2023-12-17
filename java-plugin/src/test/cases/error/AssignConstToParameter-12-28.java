import io.github.miracelwhipp.constness.annotation.Const;


class AssignConstToParameter {

    // tag::const-parameter[]
    public static void method(Object object) {

        @Const
        Object constObject = new Object();

        method(constObject) // compile error
    }
    // end::const-parameter[]

}