package entity;

import java.util.List;
import java.util.ArrayList;

public class Museum {
    protected String name;
    protected String address;
    protected String city;
    protected String country;
    protected List<Gallery> galleryList;

    public Museum(String name, String address, String city, String country) {
        setName(name);
        setAddress(address);
        setCity(city);
        setCountry(country);

        galleryList = new ArrayList<Gallery>();
    }

    public void add( Gallery gallery ) {
        gallery.setMuseum( this );
        galleryList.add( gallery );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Gallery> getGalleryList() {
        return galleryList;
    }
}
