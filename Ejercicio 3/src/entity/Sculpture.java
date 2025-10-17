package entity;

import java.util.List;
import java.util.ArrayList;

public class Sculpture extends ArtWork {
    // Tus enums
    public enum MaterialType { BRONZE, IRON, MARBLE }
    public enum SculptureType { NEOCLASSIC, GREEK_ROMAN, CUBIST }

    protected MaterialType material;
    protected SculptureType type;

    public Sculpture(String title, Author author, Gallery gallery, MaterialType material, SculptureType type) {
        super(title, author, gallery);
        this.material = material;
        this.type = type;
    }

    public MaterialType getMaterial() {
        return material;
    }

    public void setMaterial(MaterialType material) {
        this.material = material;
    }

    public SculptureType getType() {
        return type;
    }

    public void setType(SculptureType type) {
        this.type = type;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "\nMaterial: " + material + "\nTipo: " + type;
    }
}
