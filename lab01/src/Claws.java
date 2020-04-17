import java.util.Objects;

public class Claws {
    private int number;
    private int length;

    public Claws(int number, int length) {
        this.number = number;
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Claws)) return false;
        Claws claws = (Claws) o;
        return number == claws.number &&
                length == claws.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, length);
    }

    @Override
    public String toString() {
        return "Claws{" +
                "number=" + number +
                ", length=" + length +
                '}';
    }
}
