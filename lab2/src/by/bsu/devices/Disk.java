package by.bsu.devices;

import by.bsu.content.Content;

public class Disk {
    private Content content;

    public void write(Content content){
        this.content = content;
    }

    public void play(){
        content.play();
    }
}
