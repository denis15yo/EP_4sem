package by.bsu.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RuDate extends Date {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", new Locale("ru"));

    public RuDate(Date date){
        super(date.getTime());
    }

    @Override
    public String toString() {
        return DATE_FORMAT.format(this);
    }

}
