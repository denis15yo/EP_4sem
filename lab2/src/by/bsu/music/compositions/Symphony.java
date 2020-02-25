package by.bsu.music.compositions;

import by.bsu.music.Musician;
import by.bsu.music.Style;

public class Symphony extends Composition {
    public Symphony() {
    }

    public Symphony(String name, int duration, Musician musician, Style style) {
        super(name, duration, musician, style);
    }
}
