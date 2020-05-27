package by.bsu.model.car;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public enum TypeCar {
    SEDAN("img/Sedan.png"),
    AMBULANCE("img/Ambulance.png"),
    SPORT_CAR("img/Sport_car.png"),
    CABRIOLET("img/Cabriolet.png"),
    MINI_TRUCK("img/Mini_truck.png"),
    MINI_VAN("img/Mini_van.png"),
    POLICE("img/Police.png"),
    TAXI("img/Taxi.png"),
    TRUCK("img/Truck.png");

    private Image image;
    private final int width, height;

    TypeCar(String src) {
        try {
            this.image = ImageIO.read(new File(src));
        } catch (IOException e) {
            e.printStackTrace();
        }
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
