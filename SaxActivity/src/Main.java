import handler.ContactHandler;
import entity.Contact;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<Contact> contactList = new ArrayList<>();

        try {
            File inputFile = new File("contacts.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            ContactHandler handler = new ContactHandler();
            saxParser.parse(inputFile, handler);

            contactList = handler.getContacts();
            contactList.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error en la exportacion de los contactos!!");
            System.out.println("Vaciando lista de contactos.");
            contactList.clear();
        }
    }
}