package by.bsu.myUtil;

import by.bsu.music.Collection;
import by.bsu.music.compositions.Composition;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Reader {
    public static Collection<Composition> readMusicalCollectionFromXML(File file) throws ParserConfigurationException, SAXException,
            IllegalArgumentException, IOException {
        Collection<Composition> collection = new Collection<>();

        DocumentBuilderFactory f = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = f.newDocumentBuilder();
        Document document = builder.parse(file);

        collection.setName(document.getElementsByTagName("collection").item(0).getAttributes().getNamedItem("name").getTextContent());

        NodeList arias = document.getElementsByTagName("aria");
        for (int i = 0; i < arias.getLength(); ++i) {
            collection.add(Parser.parseNodeToAria(arias.item(i)));
        }

        NodeList operas = document.getElementsByTagName("opera");
        for (int i = 0; i < operas.getLength(); ++i) {
            collection.add(Parser.parseNodeToOpera(operas.item(i)));
        }

        NodeList songs = document.getElementsByTagName("song");
        for (int i = 0; i < songs.getLength(); ++i) {
            collection.add(Parser.parseNodeToSong(songs.item(i)));
        }

        NodeList symphonies = document.getElementsByTagName("symphony");
        for (int i = 0; i < symphonies.getLength(); ++i) {
            collection.add(Parser.parseNodeToSymphony(symphonies.item(i)));
        }

        return collection;
    }
}
