package io.github.miracelwhipp.constness.test;

import io.github.miracelwhipp.constness.annotation.Const;
import io.github.miracelwhipp.constness.annotation.ConstnessUtility;

public class Main {

    public static void main(String[] args) {

        Address address = new Address("city", "street 1");
        Person person = new Person("firstName", "lastName", address);

        person.getAddress().setCity("otherCity");
        person.setSurName("surName");

        @Const
        Address addressReference = address;
        @Const
        Person personReference = person;

        System.out.println(ConstnessUtility.castConstAway(addressReference.getCity()));
        System.out.println(ConstnessUtility.castConstAway(addressReference.getStreet()));
        System.out.println(ConstnessUtility.castConstAway(personReference.getAddress().getCity()));
        System.out.println(ConstnessUtility.castConstAway(personReference.getAddress().getStreet()));
    }

}
