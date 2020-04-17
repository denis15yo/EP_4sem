import java.util.Objects;

public class Predator {
    public static final int NUMBER_OF_TEETH = 32;
    public static final int NUMBER_OF_CLAWS = 10;
    public static final int CLAW_LENGTH = 5;

    private Claws claws;
    private Teeth teeth;

    public Predator() {
        claws = new Claws(NUMBER_OF_CLAWS, CLAW_LENGTH);
        teeth = new Teeth(NUMBER_OF_TEETH);
    }

    @Override
    public String toString() {
        return "Predator{" +
                "claws=" + claws +
                ", teeth=" + teeth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Predator)) return false;
        Predator predator = (Predator) o;
        return Objects.equals(claws, predator.claws) &&
                Objects.equals(teeth, predator.teeth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(claws, teeth);
    }

    public void growl(){
        System.out.println("Р-р-р");
    }
    public void run(){
        System.out.println("Бегу");
    }
    public void sleep(){
        System.out.println("Сплю");
    }
    public void getFood(){
        System.out.println("Добываю пищу");
    }
}
