package school.sorokin.javacore.oop.oopLesson;


import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Publication> publications;

    public void addPublication(Publication pb) {
        if (publications == null) {
            publications = new ArrayList<>();
        }
        publications.add(pb);
        Publication.setPublicationCount(true);
    }
}
