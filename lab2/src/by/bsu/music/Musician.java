package by.bsu.music;

import java.util.Objects;

public class Musician{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Musician() {
        name = "";
    }

    public Musician(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Musician)) return false;
        Musician musician = (Musician) o;
        return Objects.equals(name, musician.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
