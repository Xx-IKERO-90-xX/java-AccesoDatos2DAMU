package entity;

import java.util.List;
import java.util.ArrayList;

public class Gallery {
    protected String name;
    protected Museum museum;
    protected List<ArtWork> artWorkList;

    public Gallery() {
        artWorkList = new ArrayList<ArtWork>();
    }

    public Gallery(String name, Museum museum) {
        this();
        setName(name);
        setMuseum(museum);
    }

    public void add(ArtWork artWork ) {
        artWork.setGallery( this );
        artWorkList.add( artWork );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Museum getMuseum() {
        return museum;
    }

    public void setMuseum(Museum museum) {
        this.museum = museum;
    }

    public List<ArtWork> getArtWorkList() {
        return artWorkList;
    }
}