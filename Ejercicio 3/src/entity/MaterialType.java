package entity;

public class MaterialType {
    protected String description;

    public MaterialType(String description) {
        setDescription(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
