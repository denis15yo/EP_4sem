public class Main {
    public static void main(String[] args) {
        Predator predator = new Predator();
        System.out.println(predator);
        predator.run();
        predator.growl();
        predator.getFood();
        predator.sleep();
    }
}
