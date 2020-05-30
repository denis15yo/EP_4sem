package by.bsu;

import by.bsu.model.TicketsList;
import by.bsu.util.Reader;
import by.bsu.view.frames.MainFrame;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, ParseException, IOException {
        new MainFrame().setVisible(true);
    }
}
