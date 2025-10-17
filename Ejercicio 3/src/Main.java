import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;

import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;

import entity.Museum;
import entity.Gallery;
import entity.ArtWork;
import entity.Author;
import entity.Painting;
import entity.Sculpture;

public class Main {
    public static void main(String[] args) {
        // We create the Museum
        Museum myMuseum = new Museum("MARQ", "Plaza Gómez Ulla", "Alicante/Alacant", "Spain");
        List<Gallery> galleryList = myMuseum.getGalleryList();

        // I setup an gallery with an Art
        galleryList.add(new Gallery("Sala 1", myMuseum));
        galleryList.get(0).add(new ArtWork("Anonymous artwork", null, null));
        galleryList.get(0).add(
                new Painting("The Scream", new Author("Munch", "Norway"), null, Painting.PaintingType.OILPAINTING, 100, 200));
        galleryList.get(0).add(
                new Sculpture("The Thinker", new Author("Rodin", "Italy"), null, Sculpture.MaterialType.MARBLE,
                        Sculpture.SculptureType.GREEK_ROMAN));

        System.out.println("");
        System.out.println("Printing the names of the artworks");
        System.out.println("----------------------------------------------------------------");

        for (Gallery g : galleryList) {
            List<ArtWork> artworkList = g.getArtWorkList();
            System.out.println(g.getName());
            for (ArtWork a : artworkList) {
                printArtWork(a);
            }
        }
    }

    public static void printArtWork(ArtWork a) {
        System.out.println("Título: " + a.getTitle());
        if (a.getAuthor() != null)
            System.out.println("Autor: " + a.getAuthor().getName());

        if (a instanceof Painting) {
            Painting p = (Painting) a;
            System.out.println("Tipo de pintura: " + p.getType());
        }
    }
}