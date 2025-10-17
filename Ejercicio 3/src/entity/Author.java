package entity;
import java.util.List;
import java.util.ListIterator;

public class Author {
    protected String name;
    protected String nationality;

    public Author(String name, String nationality) {
        setName(name);
        setNationality(nationality);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

}
