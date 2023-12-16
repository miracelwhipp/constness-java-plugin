
import io.github.miracelwhipp.constness.test.ExampleBean;
import io.github.miracelwhipp.constness.annotation.Const;
import io.github.miracelwhipp.constness.annotation.ConstnessUtility;


class BeanFactory {


    @Const
    public static ExampleBean make() {

        ExampleBean result = new ExampleBean();

        result.setNumber(10);
        result.setText("10");

        return result;
    }

    public static void ladida() {

        @Const
        ExampleBean first = make();

        first.getNumber();

        first.getText();

        ExampleBean second = ConstnessUtility.castConstAway(first);

        second.setRecursion(make());

        second.setText("other");

        second.getRecursionConst().getNumber();
    }

}