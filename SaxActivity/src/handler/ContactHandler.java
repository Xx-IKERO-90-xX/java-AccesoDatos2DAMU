package handler;

import entity.Contact;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

// Manejador donde el Sax opera con el archivo, 
public class ContactHandler extends DefaultHandler {
    private List<Contact> contacts = new ArrayList();
    private Contact currentContact;
    private StringBuilder currentValue = new StringBuilder();
    private String currentSection = "";

    public List<Contact> getContacts() {
        return contacts;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentValue.setLength(0);

        switch (qName) {
            case "contact":
                currentContact = new Contact();
                currentContact.setId(attributes.getValue("id"));
                break;
            case "emails":
            case "phone":
                currentSection = qName;
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "contact":
                contacts.add(currentContact);
                break;

            case "name":
                currentContact.setName(currentValue.toString());
                break;

            case "surname":
                currentContact.setSurname(currentValue.toString());
                break;
            
            default:
                if (currentSection.equals("emails")) {
                    currentContact.getEmails().put(qName, currentValue.toString());
                } else if (currentSection.equals("phones")) {
                    currentContact.getPhones().put(qName, currentValue.toString());
                }
                break;
        }
    }
}