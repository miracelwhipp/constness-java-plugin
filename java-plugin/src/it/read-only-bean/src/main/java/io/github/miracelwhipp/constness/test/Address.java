package io.github.miracelwhipp.constness.test;

import io.github.miracelwhipp.constness.annotation.Const;

public class Address {

    private String city;
    private String street;

    public Address() {
    }

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }

    @Const
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Const
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
