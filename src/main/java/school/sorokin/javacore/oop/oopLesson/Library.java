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

    public void listPublications() {
        if(publications != null) {
            for (Publication list : publications) {
                System.out.println(list.toString());
            }
        } else {
            System.out.println("В списке нет публикаций.");
        }
    }

    public void searchByAuthor(String Author) {
        for (Publication list : publications) {
            if (list.getAuthor().equals(Author)) {
                System.out.println(list.toString());
            }
        }
    }
}
