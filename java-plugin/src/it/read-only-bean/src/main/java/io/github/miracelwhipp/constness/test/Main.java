package io.github.miracelwhipp.constness.test;

import io.github.miracelwhipp.constness.annotation.Const;
import io.github.miracelwhipp.constness.annotation.ConstnessUtility;

public class Main {

    public static void main(String[] args) {

        Address address = new Address("city", "street 1");
        Person person = new Person("firstName", "lastName", address);

        address.setCity("otherCity");
        person.setSurName("surName");

        @Const
        Address addressCopy = address;
        @Const
        Person personCopy = person;

        System.out.println(ConstnessUtility.castConstAway(addressCopy.getCity()));
        System.out.println(ConstnessUtility.castConstAway(addressCopy.getStreet()));
        System.out.println(ConstnessUtility.castConstAway(personCopy.getAddress().getCity()));
        System.out.println(ConstnessUtility.castConstAway(personCopy.getAddress().getStreet()));
    }

}
