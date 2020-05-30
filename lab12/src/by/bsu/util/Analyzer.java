package by.bsu.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Analyzer {
    private static final SAXParserFactory factory = SAXParserFactory.newInstance();

    public static int getTicketsCount(File file) throws ParserConfigurationException, SAXException, IOException {
        SAXParser parser = factory.newSAXParser();
        CountHandler countHandler = new CountHandler();
        parser.parse(file, countHandler);
        return countHandler.getCount();
    }

    private static class CountHandler extends DefaultHandler{
        private int count = 0;

        @Override
        public void startDocument() throws SAXException {
            count = 0;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            count += (qName.equals("ticket") ? 1 : 0);
        }

        public int getCount() {
            return count;
        }
    }
}
