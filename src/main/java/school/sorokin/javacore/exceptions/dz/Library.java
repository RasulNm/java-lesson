package school.sorokin.javacore.exceptions.dz;

import java.util.ArrayList;
import java.util.List;

public class Library {
    List<Book> catalog;

    //  возвращает список всех книг
    public List<Book> getAllBooks() throws NullPointerException {
        if (catalog == null) {
            throw new NullPointerException("В каталоге нет ни одной книги");
        }
        return catalog;
    }

    public void addBook(String title, String author, int copies) {
        if (catalog == null) {
            catalog = new ArrayList<>();
        }
        Book book = new Book(title, author, copies);
        catalog.add(book);
    }
}
