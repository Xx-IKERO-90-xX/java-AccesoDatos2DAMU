package entity;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


public class Contact {
    private String id;
    private String name;
    private String surname;
    private List<String> emails;
    private List<String> phones;

    public Contact() {
        emails = new ArrayList<>();
        phones = new ArrayList<>();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public List<String> getEmails() { return emails; }
    public List<String> getPhones() { return phones; }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", emails=" + emails +
                ", phones=" + phones +
                '}';
    }
}
