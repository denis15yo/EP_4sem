package tour;

import country.Country;

public class Tour {
    private Country country;
    private String description;
    private int price;

    public Tour(Country country, String description, int price) {
        this.country = country;
        this.description = description;
        this.price = price;
    }

    public Country getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}
