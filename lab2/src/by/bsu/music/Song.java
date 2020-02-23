package by.bsu.music;

public class Song extends Composition {
    public Song() {
    }

    public Song(String name, int duration, Musician musician, Style style) {
        super(name, duration, musician, style);
    }
}
