import by.bsu.music.Collection;
import by.bsu.music.Composition;
import by.bsu.myUtil.Reader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Collection<Composition> collection = new Collection<>();
        try {
            collection = Reader.readMusicalCollectionFromXML(new File("collection.xml"));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        collection.print();
    }
}
