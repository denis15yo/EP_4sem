package by.bsu.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Flight implements Serializable {
    private static final long serialVersionUID = 1L;

    private final City from;
    private final City to;
    private final RuDate departureDate;
    private final RuDate arrivalDate;

    public Flight(City from, City to, RuDate departureDate, RuDate arrivalDate) {
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "from=" + from +
                ", to=" + to +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                '}';
    }
}
