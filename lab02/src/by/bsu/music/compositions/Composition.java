package by.bsu.music.compositions;

import by.bsu.music.Musician;
import by.bsu.music.Style;

public abstract class Composition {
    private String name;
    private int duration;
    private Musician musician;
    private Style style;

    public Composition() {
    }

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

    @Override
    public String toString() {
        return String.format("%-8s%-42s%-25s%-33s%-30s" ,
                getClass().getSimpleName(),
                " : (название: " + name,
                ", продолжительность: " + duration,
                ", исполнитель: " + musician,
                ", стиль: " + style + ')') ;
    }
}
