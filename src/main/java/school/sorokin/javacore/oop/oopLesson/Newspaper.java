package school.sorokin.javacore.oop.oopLesson;

import java.util.Objects;

public class Newspaper extends Publication implements Printable{

    private String publicationDay;

    public Newspaper(String title, String author, int year) {
        super(title, author, year);
    }

    @Override
    public String getType() {
        return "Газета";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Newspaper newspaper = (Newspaper) obj;
        return publicationDay.equals(newspaper.publicationDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), publicationDay);
    }

    @Override
    public String toString() {
        return "Newspaper{" + super.toString() +
                ", publicationDay='" + publicationDay + '\'' +
                '}';
    }

    public String getPublicationDay() {
        return publicationDay;
    }

    public void setPublicationDay(String publicationDay) {
        this.publicationDay = publicationDay;
    }

    @Override
    public void printDetails() {
        System.out.println("Название публикации: " + super.getTitle() +
                ", автор публикации: " + super.getAuthor() +
                ", год выпуска: " + super.getYear() +
                ", день публикации: " + getPublicationDay());
    }
}
