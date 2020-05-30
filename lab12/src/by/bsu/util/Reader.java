package by.bsu.util;

import by.bsu.model.Ticket;
import by.bsu.model.TicketsList;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.text.ParseException;

public class Reader {
    public static TicketsList readTicketsListFromXML(File file) throws ParserConfigurationException, IOException, SAXException, ParseException {
        TicketsList ticketsList = new TicketsList();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        Document document = builder.parse(file);

        NodeList nodeList = document.getElementsByTagName("ticket");
        for(int i = 0; i < nodeList.getLength(); ++i){
            ticketsList.add(Parser.parseNodeToTicket(nodeList.item(i)));
        }

        return ticketsList;
    }

    public static TicketsList readTicketsListFromBinaryFile(File file) throws IOException, ClassNotFoundException {
        FileInputStream inputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return (TicketsList) objectInputStream.readObject();
    }
}
