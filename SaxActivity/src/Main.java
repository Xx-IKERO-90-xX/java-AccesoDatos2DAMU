import handler.ContactHandler;
import entity.Contact;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("contacts.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            ContactHandler handler = new ContactHandler();
            saxParser.parse(inputFile, handler);

            List<Contact> contactList = handler.getContacts();
            contactList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}