package by.bsu.model;

import java.io.Serializable;

public class City implements Serializable {
    private static final long serialVersionUID = 1L;


    private final String name;
    private final String country;

    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return name + "(" + country + ")";
    }
}
