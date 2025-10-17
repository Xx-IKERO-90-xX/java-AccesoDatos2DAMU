package entity;

public class PaintingType {
    protected String description;

    public PaintingType (String description) {
        setDescription(description);
        getDescription(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription(String description) {
        return description;
    }
}
