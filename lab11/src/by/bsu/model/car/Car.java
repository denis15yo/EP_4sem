package by.bsu.model.car;

import by.bsu.interfaces.Drawable;

import java.awt.*;

public class Car implements Drawable {
    private int x;
    private int y;
    private int speed;
    private final boolean isPassingDirection;
    private final TypeCar typeCar;

    public Car(int x, int y, int speed, TypeCar typeCar) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        isPassingDirection = (speed <= 0);
        this.typeCar = typeCar;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getSpeed() {
        return speed;
    }

    public int getWidth(){
        return typeCar.getWidth();
    }
    public int getHeight(){
        return typeCar.getHeight();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setX(int x) {
        this.x = x;
    }


    public void vMove(int vDelta){
        y += vDelta;
    }

    @Override
    public void draw(Graphics g) {
        if(!isPassingDirection){
            g.drawImage(typeCar.getImage(), x, y + typeCar.getHeight(), typeCar.getWidth(), - typeCar.getHeight(), null);
        }
        else{
            g.drawImage(typeCar.getImage(), x, y, null);
        }
    }
}
