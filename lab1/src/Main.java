public class Main {
    public static void main(String[] args) {
        Predator predator = new Predator(new Claws(), new Teeth());
        predator.run();
        predator.growl();
        predator.getFood();
        predator.sleep();
    }
}
