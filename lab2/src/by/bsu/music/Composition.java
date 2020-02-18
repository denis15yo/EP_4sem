package by.bsu.music;

public abstract class Composition {
    private String name;
    private int duration;
    private Musician musician;
    private Style style;

    public Composition(String name, int duration, Musician musician, Style style) {
        this.name = name;
        this.duration = duration;
        this.musician = musician;
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Musician getMusician() {
        return musician;
    }

    public void setMusician(Musician musician) {
        this.musician = musician;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
