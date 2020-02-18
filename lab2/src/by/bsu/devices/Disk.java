package by.bsu.devices;

import by.bsu.content.Content;

public class Disk {
    private String name;
    private Content content;

    public Disk(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void write(Content content){
        this.content = content;
    }

    public void play(){
        content.play();
    }
}
