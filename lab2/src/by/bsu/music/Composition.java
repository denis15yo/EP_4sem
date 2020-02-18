package by.bsu.music;

public abstract class Composition {
    private int duration;
    private Musician musician;
    private Style style;

    public Composition(int duration, Musician musician) {
        this.duration = duration;
        this.musician = musician;
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
