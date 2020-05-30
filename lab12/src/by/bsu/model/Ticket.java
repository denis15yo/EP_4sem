package by.bsu.model;

import java.io.Serializable;

public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;


    private final int id;
    private final Flight flight;
    private final Person person;
    private final int placeNumber;
    private final int cost;

    public Ticket(int id, Flight flight, Person person, int placeNumber, int cost) {
        this.id = id;
        this.flight = flight;
        this.person = person;
        this.placeNumber = placeNumber;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public Flight getFlight() {
        return flight;
    }

    public Person getPerson() {
        return person;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", flight=" + flight +
                ", person=" + person +
                ", placeNumber=" + placeNumber +
                ", cost=" + cost +
                '}';
    }
}
