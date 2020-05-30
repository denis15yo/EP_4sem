package by.bsu.util;

import by.bsu.model.*;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.ParseException;
import java.util.Date;

public class Parser {
    public static Ticket parseNodeToTicket(Node node) throws ParseException {
        int id = -1;
        try{
            id = Integer.parseInt(node.getAttributes().getNamedItem("id").getTextContent());
        } catch(NumberFormatException ex){
            throw new ParseException("Некорректный id", 0);
        }

        Flight flight = null;
        Person person = null;
        int placeNumber = -1;
        int cost = -1;

        Node currentChild = node.getFirstChild();
        while(currentChild != null){
            String childName = currentChild.getNodeName();
            switch(childName){
                case "flight":
                    flight = parseNodeToFlight(currentChild);
                    break;
                case "person":
                    person = parseNodeToPerson(currentChild);
                    break;
                case "cost":
                    try{
                        cost = Integer.parseInt(currentChild.getTextContent());
                    } catch (NumberFormatException ex){
                        throw new ParseException("Некорректная стоимость билета", 0);
                    }

                    break;
                case "placeNumber":
                    try{
                        placeNumber = Integer.parseInt(currentChild.getTextContent());
                    } catch (NumberFormatException ex){
                        throw new ParseException("Некорреткный номер сиденья", 0);
                    }
            }
            currentChild = currentChild.getNextSibling();
        }

        return new Ticket(id, flight, person, placeNumber, cost);
    }

    public static Flight parseNodeToFlight(Node node) throws ParseException {
        City from = null;
        City to = null;
        RuDate departureDate = null;
        RuDate arrivalDate = null;

        Node currentChild = node.getFirstChild();
        while(currentChild != null){
            String childName = currentChild.getNodeName();
            switch(childName){
                case "city":
                    String attr = currentChild.getAttributes().getNamedItem("attr").getTextContent();
                    if(attr.equals("from")){
                        from = parseNodeToCity(currentChild);
                    } else if(attr.equals("to")){
                        to = parseNodeToCity(currentChild);
                    }
                    break;
                case "date":
                    attr = currentChild.getAttributes().getNamedItem("attr").getTextContent();
                    if(attr.equals("departure")){
                        departureDate = parseToRuDate(currentChild.getTextContent());
                    } else if(attr.equals("arrival")){
                        arrivalDate = parseToRuDate(currentChild.getTextContent());
                    }
                    break;
            }
            currentChild = currentChild.getNextSibling();
        }

        return new Flight(from, to, departureDate, arrivalDate);
    }

    public static City parseNodeToCity(Node node) {
        String name = null;
        String country = null;

        Node currentChild = node.getFirstChild();
        while(currentChild != null){
            String childName = currentChild.getNodeName();
            switch(childName){
                case "name":
                    name = currentChild.getTextContent();
                    break;
                case "country":
                    country = currentChild.getTextContent();
                    break;
            }
            currentChild = currentChild.getNextSibling();
        }

        return new City(name , country);
    }

    public static Person parseNodeToPerson(Node node) throws ParseException {
        String name = null;
        String surname = null;
        String passportID = node.getAttributes().getNamedItem("passportID").getTextContent();
        int age = 0;

        Node currentChild = node.getFirstChild();
        while(currentChild != null){
            String childName = currentChild.getNodeName();
            switch(childName){
                case "name":
                    name = currentChild.getTextContent();
                    break;
                case "surname":
                    surname = currentChild.getTextContent();
                    break;
                case "age":
                    try{
                        age = Integer.parseInt(currentChild.getTextContent());
                    } catch (NumberFormatException ex){
                        throw new ParseException("Некорректный возраст", 0);
                    }
                    break;
            }
            currentChild = currentChild.getNextSibling();
        }

        return new Person(name, surname, passportID, age);
    }

    public static RuDate parseToRuDate(String str) throws ParseException {
       return new RuDate(RuDate.DATE_FORMAT.parse(str));
    }
}
