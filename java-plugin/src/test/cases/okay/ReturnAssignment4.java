import io.github.miracelwhipp.constness.annotation.Const;



class ReturnAssignment {

    @Const
    private Object object;

    @Const
    public Object getObject() {

        return object;
    }

}