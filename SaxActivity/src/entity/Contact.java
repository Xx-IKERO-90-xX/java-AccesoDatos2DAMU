package entity;

import java.util.HashMap;
import java.util.Map;

public class Contact {
    private String id;
    private String name;
    private String surname;
    private Map<String, String> emails = new HashMap<>();
    private Map<String, String> phones = new HashMap<>();

    public contact () {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName() { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname() { this.surname = surname; }

    public Map<String, String> getEmails() { return emails; }
    public Map<String, String> getPhones() { return phones; }

    @Override
    public String toString() {
        return "Contact{id='" + id + "', name='" + name + "', surname='" + surname +
                "', emails=" + emails + ", phones=" + phones + "}";
    }

}
