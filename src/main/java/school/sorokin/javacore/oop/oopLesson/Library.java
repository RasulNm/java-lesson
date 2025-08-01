package school.sorokin.javacore.oop.oopLesson;


import java.util.ArrayList;
import java.util.Iterator;
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

    public void deletePublication(String Author) {
        if (publications != null) {
            Iterator<Publication> listIterator = publications.iterator();
            while (listIterator.hasNext()) {
                Publication publication = listIterator.next();
                if (publication.getAuthor().equals(Author)) {
                    listIterator.remove();
                    System.out.println(publication + " ---> удален.");
                    Publication.setPublicationCount(false);
                }
            }
        } else {
            System.out.println("Ошибка: список публикаций пуст");
        }
    }
}
