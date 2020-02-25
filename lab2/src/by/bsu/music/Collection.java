package by.bsu.music;

import by.bsu.content.Content;
import by.bsu.music.compositions.Composition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class Collection<T extends Composition> extends ArrayList<T> implements Content {
    private String name = "unknown-collection";



    public void setName(String name) {
        this.name = name;
    }

    public int duration(){
        return stream().mapToInt(Composition::getDuration).sum();
    }

    public void sortByStyle(){
        sort(Comparator.comparing(Composition::getStyle));
    }

    public Optional<T> search(int minDuration, int maxDuration){
        return stream().filter(e -> (e.getDuration() >= minDuration && e.getDuration() <= maxDuration)).findFirst();
    }

    @Override
    public void play() {
        System.out.println("Играет музыкальная коллекция " + name);
    }

    public void print() {
        forEach(System.out::println);
    }
}