package by.bsu.model;

import by.bsu.events.GameOverEvent;
import by.bsu.events.SpawnEvent;
import by.bsu.events.GameLoopTimerEvent;
import by.bsu.interfaces.Drawable;
import by.bsu.interfaces.Observable;
import by.bsu.interfaces.Observer;
import by.bsu.model.car.Car;
import by.bsu.model.car.PlayerCar;
import by.bsu.model.car.TypeCar;
import by.bsu.model.road.Road;
import by.bsu.model.road.TypeRoad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@SuppressWarnings("RedundantIfStatement")
public class Game implements ActionListener, Observable, Drawable {
    private final Set<Observer> observers = new HashSet<>();

    private final Timer gameLoopTimer;
    private final Timer spawnTimer;
    private final Timer cleanCarsTimer;

    private static final int DELAY = 16;
    private static final int CLEAN_CARS_DELAY = 5000;

    private final int level;

    private final Player player;

    private final Road road;
    private final PlayerCar playerCar;
    private final Set<Car> cars;

    private static final int ROAD_SPEED = 2;

    private static final int COLLISION_OFFSET = 10;

    private static final int PROBABILITY_COEFFICIENT_ONCOMING_TRAFFIC = 3;

    private Car policeCar;
    private static final int POLICE_CAR_SPEED = 3;

    private State state;

    public Game(Player player, TypeRoad typeRoad, TypeCar typeCar, int level) {
        gameLoopTimer = new Timer(DELAY, this);
        spawnTimer = new Timer(getSpawnDelayByLevel(level), this);
        cleanCarsTimer = new Timer(CLEAN_CARS_DELAY, this);

        this.player = player;

        road = new Road(typeRoad, ROAD_SPEED);

        this.level = level;
        playerCar = new PlayerCar((road.getLeftBorder() + road.getRightBorder()) / 2,
                road.getDownBorder() - typeCar.getHeight() - 40, -ROAD_SPEED, typeCar);

        cars = new HashSet<>();

        policeCar = null;

        state = State.ACTIVE;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(gameLoopTimer)){
            moveAll();
            if(state == State.ACTIVE){
                Car collisionCar = checkCollisions(playerCar);
                player.setScores(player.getScores() + road.getSpeed());
                if(collisionCar != null){
                    spawnTimer.stop();
                    collisionCar.setSpeed(0);
                    road.setSpeed(0);
                    state = State.WAITING_FOR_CLEAN_CARS;
                }
                updateAllObservers(new GameLoopTimerEvent(this));
            } else if(state == State.WAITING_FOR_CLEAN_CARS){
                int countOfCollisions = 0;
                for(Car car : cars){
                    Car collisionCar = checkCollisions(car);
                    if(collisionCar != null){
                        ++countOfCollisions;
                        collisionCar.setSpeed(0);
                    }
                }
                countOfCollisions /= 2;
                for(Car car : cars){
                    if(checkCollision(car, playerCar)){
                        car.setSpeed(0);
                        ++countOfCollisions;
                    }
                }
                cars.removeIf(car -> car.getY() > road.getDownBorder() || car.getY() < road.getUpBorder() - car.getHeight());
                if(cars.size() == countOfCollisions){
                    spawnPolice();
                    state = State.WAITING_FOR_POLICE;
                }
                updateAllObservers(new GameLoopTimerEvent(this));
            } else if(state == State.WAITING_FOR_POLICE){
                if(policeCar.getY() >= playerCar.getY()){
                    state = State.END_GAME;
                    stop();
                    updateAllObservers(new GameOverEvent(this));
                }
                updateAllObservers(new GameLoopTimerEvent(this));
            }
        }
        else if(e.getSource().equals(spawnTimer)){
            spawn();
            updateAllObservers(new SpawnEvent(this));
        }
        else if(e.getSource().equals(cleanCarsTimer)){
            cars.removeIf(car -> car.getY() > road.getDownBorder());
        }
    }

    public void start(){
        gameLoopTimer.start();
        spawnTimer.start();
        cleanCarsTimer.start();
    }
    public void stop(){
        gameLoopTimer.stop();
        spawnTimer.stop();
        cleanCarsTimer.stop();
    }

    public void hMovePlayer(int hDelta){
        if(state == State.ACTIVE){
            playerCar.hMove(hDelta);
            if(playerCar.getX() < road.getLeftBorder()){
                playerCar.setX(road.getLeftBorder());
            }
            if(playerCar.getX() > road.getRightBorder() - playerCar.getWidth()){
                playerCar.setX(road.getRightBorder() - playerCar.getWidth());
            }
        }
    }

    public void spawn(){
        int leftBorder = road.getLeftBorder();
        int rightBorder = road.getRightBorder();
        Random random = new Random();
        TypeCar typeCar = TypeCar.values()[random.nextInt(TypeCar.values().length)];
        int x;
        int speed;
        if(random.nextInt() % PROBABILITY_COEFFICIENT_ONCOMING_TRAFFIC != 0){
            x = random.nextInt((rightBorder - leftBorder) / 2 - typeCar.getWidth()) + leftBorder;
            speed = getSpeedByLevel(level);
        }
        else{
            x = random.nextInt((rightBorder - leftBorder) / 2 - typeCar.getWidth()) + (rightBorder + leftBorder) / 2;
            speed = -ROAD_SPEED + 1;
        }
        Car car = new Car(x, -typeCar.getHeight(), speed, typeCar);
        if(checkCollisions(car) == null){
            cars.add(new Car(x, -typeCar.getHeight(), speed, typeCar));
        }
    }

    private void spawnPolice(){
        int delta = 2 * playerCar.getWidth();
        int xPolice = (playerCar.getX() + delta < (road.getRightBorder() - TypeCar.POLICE.getWidth())) ?
                playerCar.getX() + delta : playerCar.getX() - delta;
        policeCar = new Car(xPolice, -TypeCar.POLICE.getHeight(), POLICE_CAR_SPEED, TypeCar.POLICE);
        cars.add(policeCar);
    }

    private boolean checkCollision(Car firstCar, Car secondCar){
        if(firstCar.equals(secondCar)){
            return false;
        }
        Rectangle rect = new Rectangle(firstCar.getX(), firstCar.getY(),
                firstCar.getWidth(), firstCar.getHeight());
        if(rect.contains(secondCar.getX() + COLLISION_OFFSET,
                secondCar.getY() + COLLISION_OFFSET)){
            return true;
        }
        if(rect.contains(secondCar.getX() + secondCar.getWidth() - COLLISION_OFFSET,
                secondCar.getY() + COLLISION_OFFSET)){
            return true;
        }
        if(rect.contains(secondCar.getX() + COLLISION_OFFSET,
                secondCar.getY() + secondCar.getHeight() - COLLISION_OFFSET)){
            return true;
        }
        if(rect.contains(secondCar.getX() + secondCar.getWidth() - COLLISION_OFFSET,
                secondCar.getY() + secondCar.getHeight() - COLLISION_OFFSET)){
            return true;
        }
        return false;
    }


    private Car checkCollisions(Car withCar){
        for(Car car : cars){
            if(checkCollision(car, withCar)){
                return car;
            }
        }

        return null;
    }

    private void moveAll(){
        road.move();
        cars.forEach(car -> car.vMove(road.getSpeed() + car.getSpeed()));
    }

    private int getSpeedByLevel(int level){
        return ROAD_SPEED + level;
    }
    private int getSpawnDelayByLevel(int level){
        switch (level){
            case 1: return 1500;
            case 2: return 1000;
            case 3: return 500;
        }
        return 1000;
    }

    public int getWidth(){
        return road.getWidth();
    }
    public int getHeight(){
        return road.getHeight();
    }
    public Player getPlayer() {
        return player;
    }

    public void changeSpeed(int delta){
        if(state == State.ACTIVE){
            int newSpeed = road.getSpeed() + delta;
            road.setSpeed(Math.max(newSpeed, 0));
        }
    }

    @Override
    public void draw(Graphics g) {
        road.draw(g);
        cars.forEach(car -> car.draw(g));
        playerCar.draw(g);
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void updateAllObservers(EventObject eventObject) {
        observers.forEach(observer -> observer.update(eventObject));
    }
}
