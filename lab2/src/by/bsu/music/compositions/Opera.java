package by.bsu.music.compositions;

import by.bsu.music.Musician;
import by.bsu.music.Style;

public class Opera extends Composition {
    public Opera() {
    }

    public Opera(String name, int duration, Musician musician, Style style) {
        super(name, duration, musician, style);
    }
}
