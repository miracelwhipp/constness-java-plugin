import io.github.miracelwhipp.constness.annotation.Const;



class ReturnAssignment {

    @Const
    private Object object;


    public Object getObject() {

        return object; // compile error
    }

}