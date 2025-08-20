package school.sorokin.javacore.exceptions.dz;

public class Book {
    String title; //название книги
    String author; // имя автора
    int availableCopies; // количество доступных экзмепляров

    public Book(String title, String author, int availableCopies) {
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", availableCopies=" + availableCopies +
                '}';
    }
}
