package io.github.miracelwhipp.constness.test;

import io.github.miracelwhipp.constness.annotation.Const;

public class Person {

    private String firstName;
    private String surName;
    private Address address;

    public Person() {
    }

    public Person(String firstName, String surName, Address address) {
        this.firstName = firstName;
        this.surName = surName;
        this.address = address;
    }

    @Const
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Const
    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @Const
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
