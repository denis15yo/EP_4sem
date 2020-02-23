package by.bsu.myUtil;

import by.bsu.music.*;
import org.w3c.dom.Node;

public class Parser {
    public static Aria parseNodeToAria(Node node){
        Aria aria = new Aria();
        getCompositionInfo(node, aria);
        return aria;
    }
    public static Opera parseNodeToOpera(Node node){
        Opera opera = new Opera();
        getCompositionInfo(node, opera);
        return opera;
    }
    public static Song parseNodeToSong(Node node){
        Song song = new Song();
        getCompositionInfo(node, song);
        return song;
    }
    public static Symphony parseNodeToSymphony(Node node){
        Symphony symphony = new Symphony();
        getCompositionInfo(node, symphony);
        return symphony;
    }

    public static Musician parseNodeToMusician(Node node){
        Musician musician = new Musician();
        Node currentChild = node.getFirstChild();
        while(currentChild != null){
            String childName = currentChild.getNodeName();
            if ("name".equals(childName)) {
                musician.setName(currentChild.getTextContent());
            }
            currentChild = currentChild.getNextSibling();
        }
        return musician;
    }

    private static void getCompositionInfo(Node node, Composition composition){
        Node currentChild = node.getFirstChild();
        while(currentChild != null){
            String childName = currentChild.getNodeName();
            switch(childName){
                case "name":
                    composition.setName(currentChild.getTextContent());
                    break;
                case "style":
                    composition.setStyle(Style.valueOf(currentChild.getTextContent()));
                    break;
                case "duration":
                    composition.setDuration(Integer.parseInt(currentChild.getTextContent()));
                    break;
                case "musician":
                    composition.setMusician(parseNodeToMusician(currentChild));
                    break;
            }
            currentChild = currentChild.getNextSibling();
        }
    }

}