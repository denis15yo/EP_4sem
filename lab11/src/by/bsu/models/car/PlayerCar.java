package by.bsu.models.car;

public class PlayerCar extends Car {
    public int distance;

    public PlayerCar(int x, int y, int speed, TypeCar typeCar) {
        super(x, y, speed, typeCar);
        distance = 0;
    }

    public int getDistance() {
        return distance;
    }

    public void vMove(){
        distance += Math.abs(getSpeed());
    }

    public void hMove(int hDelta){
        setX(getX() + hDelta);
    }
}
