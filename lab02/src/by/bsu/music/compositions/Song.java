package by.bsu.music.compositions;

import by.bsu.music.Musician;
import by.bsu.music.Style;

public class Song extends Composition {
    public Song() {
    }

    public Song(String name, int duration, Musician musician, Style style) {
        super(name, duration, musician, style);
    }
}
