package school.sorokin.javacore.exceptions.dz;

import org.slf4j.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Library {
    private static final Logger log = LoggerFactory.getLogger(Library.class);
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

    // уменьшает copies или бросает исключение
    public void takeBook(String title) throws NoAvailableCopiesException, NoSuchElementException {
        for (Book catalog : catalog) {
            if (title.equals(catalog.title)) {
                if (catalog.availableCopies > 0) {
                    catalog.availableCopies--;
                    log.debug("Пользователь взял книгу: " + title + ". Количество копий уменьшилось на 1.");
                    return;
                } else {
                    throw new NoAvailableCopiesException("Книга есть в списке, но все копии выданы.");
                }
            }
        }
        throw new NoSuchElementException("Ошибка: книга с таким названием отсутствует в списке.");
    }
}
