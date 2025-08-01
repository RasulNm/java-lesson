package school.sorokin.javacore.oop.oopLesson;

import java.util.Objects;

public class Book extends Publication implements Printable{

    private String ISBN;

    public Book(String title, String author, int year) {
        super(title, author, year);
    }

    @Override
    public String getType() {
        return "Книга";
    }

    @Override
    public boolean equals(Object obj){
        if(!super.equals(obj)) return false;
        Book book = (Book) obj;
        return ISBN.equals(book.ISBN);
    }

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), ISBN);
    }

    @Override
    public String toString(){
        return "Book{" + super.toString() +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public void printDetails() {
        System.out.println("Название публикации: " + super.getTitle() +
                ", автор публикации: " + super.getAuthor() +
                ", год выпуска: " + super.getYear() +
                ", уникальный идентификационный номер издания: " + getISBN());
    }
}
