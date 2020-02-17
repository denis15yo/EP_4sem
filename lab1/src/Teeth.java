import java.util.Objects;

public class Teeth {
    private int number;

    public Teeth(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teeth)) return false;
        Teeth teeth = (Teeth) o;
        return number == teeth.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Teeth{" +
                "number=" + number +
                '}';
    }
}
