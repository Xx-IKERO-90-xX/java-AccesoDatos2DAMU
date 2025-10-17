package entity;

import java.util.List;
import java.util.ArrayList;

public class ArtWork {
    protected String title;
    protected Author author;
    protected Gallery gallery;


    public ArtWork(String title, Author author, Gallery gallery) {
        setTitle(title);
        setAuthor(author);
        setGallery(gallery);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }

    public String getDescription() {
        return getTitle() + "\n" + (getAuthor() == null ? "Anonymous" : getAuthor().getName());
    }
}
