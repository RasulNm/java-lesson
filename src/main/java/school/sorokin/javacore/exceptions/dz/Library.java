package school.sorokin.javacore.exceptions.dz;

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
}
