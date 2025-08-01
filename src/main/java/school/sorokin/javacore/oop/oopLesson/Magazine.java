package school.sorokin.javacore.oop.oopLesson;

import java.util.Objects;

public class Magazine extends Publication implements Printable{

    private int issueNumber;

    public Magazine(String title, String author, int year) {
        super(title, author, year);
    }

    @Override
    public String getType() {
        return "Журнал";
    }

    @Override
    public boolean equals(Object obj){
        if(!super.equals(obj)) return false;
        Magazine magazine = (Magazine) obj;
        return issueNumber == magazine.issueNumber;
    }

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), issueNumber);
    }

    @Override
    public String toString(){
        return "Magazine{" + super.toString() +
                ", issueNumber=" + issueNumber +
                '}';
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public void printDetails() {
        System.out.println("Название публикации: " + super.getTitle() +
                ", автор публикации: " + super.getAuthor() +
                ", год выпуска: " + super.getYear() +
                ", номер выпуска: " + getIssueNumber());
    }
}
