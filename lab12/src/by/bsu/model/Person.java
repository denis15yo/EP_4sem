package by.bsu.model;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;


    private final String name;
    private final String surname;
    private final String passportID;
    private final int age;

    public Person(String name, String surname, String passportID, int age) {
        this.name = name;
        this.surname = surname;
        this.passportID = passportID;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassportID() {
        return passportID;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", passportID='" + passportID + '\'' +
                ", age=" + age +
                '}';
    }
}
