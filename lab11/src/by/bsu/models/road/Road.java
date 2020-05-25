package by.bsu.models.road;

import by.bsu.interfaces.Drawable;

import java.awt.*;

public class Road implements Drawable {
    private final TypeRoad typeRoad;

    private final Image bg;
    private int speed;

    private int y1, y2;

    public Road(TypeRoad typeRoad, int speed) {
        this.typeRoad = typeRoad;
        bg = typeRoad.getBg();
        y1 = 0;
        y2 = - bg.getHeight(null);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWidth(){
        return bg.getWidth(null);
    }
    public int getHeight(){
        return  bg.getHeight(null);
    }

    public int getLeftBorder(){
        return typeRoad.getBounds().x;
    }
    public int getRightBorder(){
        return typeRoad.getBounds().x + typeRoad.getBounds().width;
    }
    public int getUpBorder(){
        return typeRoad.getBounds().y;
    }
    public int getDownBorder(){
        return typeRoad.getBounds().y + typeRoad.getBounds().height;
    }

    public void move(){
        y1 += speed;
        y2 += speed;
        if(y2 >= 0){
            y1 = 0;
            y2 = -bg.getHeight(null);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bg, 0, y1, null);
        g.drawImage(bg, 0, y2, null);
    }
}
