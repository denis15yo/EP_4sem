package by.bsu.model.car;

public class PlayerCar extends Car {
    public PlayerCar(int x, int y, int speed, TypeCar typeCar) {
        super(x, y, speed, typeCar);
    }

    public void hMove(int hDelta){
        setX(getX() + hDelta);
    }
}
