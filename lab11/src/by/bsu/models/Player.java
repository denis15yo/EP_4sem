package by.bsu.models;

import by.bsu.models.car.TypeCar;

public class Player {
   private final String name;
   private final TypeCar typeCar;

    public Player(String name, TypeCar typeCar) {
        this.name = name;
        this.typeCar = typeCar;
    }

    public String getName() {
        return name;
    }

    public TypeCar getTypeCar() {
        return typeCar;
    }
}
