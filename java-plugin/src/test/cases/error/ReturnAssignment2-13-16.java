import io.github.miracelwhipp.constness.annotation.Const;
import io.github.miracelwhipp.constness.annotation.ConstMethod;


class ReturnAssignment {

    @Const
    private Object object;

    @ConstMethod
    public Object getObject() {

        return object; // compile error
    }

}