package by.bsu.util;

import by.bsu.model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Date;

public class Saver {
    public static void saveToXML(TicketsList ticketsList, File file) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = f.newDocumentBuilder();
        Document document = builder.newDocument();

        Element rootElement = document.createElement("tickets");
        document.appendChild(rootElement);

        ticketsList.forEach(t -> rootElement.appendChild(getTicketAsElement(t, document)));

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        StreamResult streamResult = new StreamResult(file);

        transformer.transform(source, streamResult);
        System.out.println("Создание XML файла закончено");
    }

    public static void saveToBinaryFormat(TicketsList ticketsList, File file) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(ticketsList);
        objectOutputStream.close();
    }

    public static Element getTicketAsElement(Ticket ticket, Document document){
        Element element = document.createElement("ticket");

        element.setAttribute("id", Integer.toString(ticket.getId()));
        element.appendChild(getFlightAsElement(ticket.getFlight(), document));
        element.appendChild(getPersonAsElement(ticket.getPerson(), document));
        element.appendChild(createElement("placeNumber", ticket.getPlaceNumber(), document));
        element.appendChild(createElement("cost", ticket.getCost(), document));

        return element;
    }

    public static Element getFlightAsElement(Flight flight, Document document){
        Element element = document.createElement("flight");

        Element fromCityElement = getCityAsElement(flight.getFrom(), document);
        fromCityElement.setAttribute("attr", "from");
        element.appendChild(fromCityElement);

        Element toCityElement = getCityAsElement(flight.getTo(), document);
        toCityElement.setAttribute("attr", "to");
        element.appendChild(toCityElement);



        Element departureDateElement = createElement("date", flight.getDepartureDate(), document);
        departureDateElement.setAttribute("attr", "departure");
        element.appendChild(departureDateElement);

        Element arrivalDateElement = createElement("date", flight.getArrivalDate(), document);
        arrivalDateElement.setAttribute("attr", "arrival");
        element.appendChild(arrivalDateElement);

        return element;
    }

    public static Element getCityAsElement(City city, Document document){
        Element element = document.createElement("city");

        element.appendChild(createElement("name", city.getName(), document));
        element.appendChild(createElement("country", city.getCountry(), document));

        return element;
    }


    public static Element getPersonAsElement(Person person, Document document){
        Element element = document.createElement("person");

        element.setAttribute("passportID", person.getPassportID());

        element.appendChild(createElement("name", person.getName(), document));
        element.appendChild(createElement("surname", person.getSurname(), document));
        element.appendChild(createElement("age", person.getAge(), document));

        return element;
    }

    private static Element createElement(String tagName, Object value, Document document){
        Element element = document.createElement(tagName);
        element.setTextContent(value.toString());
        return element;
    }
}
