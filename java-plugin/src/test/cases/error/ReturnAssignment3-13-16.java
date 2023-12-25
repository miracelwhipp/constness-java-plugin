import io.github.miracelwhipp.constness.annotation.Const;
import io.github.miracelwhipp.constness.annotation.PreservesConst;


class ReturnAssignment {

    @Const
    private Object object;

    @PreservesConst
    public Object getObject() {

        return object; // compile error
    }

}