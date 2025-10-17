package entity;

import java.util.List;
import java.util.ArrayList;

public class Painting extends ArtWork {
    public enum PaintingType {
        OILPAINTING,
        WATERCOLOR,
        ACRYLIC
    }

    protected PaintingType type;
    protected double width;
    protected double height;

    public Painting(String title, Author author, Gallery gallery, PaintingType type, double width, double height) {
        super(title, author, gallery);
        setType(type);
        setWidth(width);
        setHeight(height);
    }

    public PaintingType getType() {
        return type;
    }

    public void setType(PaintingType type) {
        this.type = type;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "\n" + getType();
    }
}
