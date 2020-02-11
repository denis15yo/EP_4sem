public class Predator {
    private Claws claws;
    private Teeth teeth;

    public Predator(Claws claws, Teeth teeth) {
        this.claws = claws;
        this.teeth = teeth;
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
