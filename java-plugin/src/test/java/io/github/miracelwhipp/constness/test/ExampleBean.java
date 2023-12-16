package io.github.miracelwhipp.constness.test;

import io.github.miracelwhipp.constness.annotation.Const;

public class ExampleBean {

    private int number;
    private String text;
    private ExampleBean recursion;

    @Const
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Const
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Const
    public ExampleBean getRecursionConst() {
        return recursion;
    }

    public ExampleBean getRecursion() {
        return recursion;
    }

    public void setRecursion(@Const ExampleBean recursion) {
    }
}
