package school.sorokin.javacore.oop.oopLesson;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        int numberOfOptions = 0;
        boolean isExitFromCycle = true;
        boolean validInput = true;

        while (isExitFromCycle) {

            while (validInput) {
                try {
                    System.out.println("Выберите опцию (введите номер): " +
                            "\n1 - Добавить новую публикацию.\n" +
                            "2 - Вывести список всех публикаций.\n" +
                            "3 - Поиск публикации по автору.\n" +
                            "4 - Удаление публикации по автору.\n" +
                            "5 - Вывести общее количество публикаций (используя статический метод).\n" +
                            "0 - Выход");
                    System.out.print("Введите номер опции (0-5): ");
                    numberOfOptions = scanner.nextInt();
                    validInput = false;
                } catch (InputMismatchException e) {
                    System.out.println("---------------------------------------");
                    System.out.println("ОШИБКА! Введите целое число (0-5).");
                    System.out.println("---------------------------------------");
                    scanner = new Scanner(System.in);
                }
            }

            validInput = true;

            switch (numberOfOptions) {
                case 1:
                    int publicationTypeNumber = 0;
                    while (validInput) {
                        System.out.println("---------------------------------------");
                        System.out.println("Выберите тип публикации: \n1 - Book. \n2 - Magazine. \n3 - Newspaper.");
                        System.out.print("Введите номер типа публикации (1-3): ");
                        if (scanner.hasNextInt()) {
                            publicationTypeNumber = scanner.nextInt();
                            validInput = false;
                        } else {
                            System.out.println("---------------------------------------");
                            System.out.println("ОШИБКА! Введите целое число (1-3).");
                            scanner = new Scanner(System.in);

                        }
                    }

                    validInput = true;

                    if (publicationTypeNumber == 1 || publicationTypeNumber == 2 || publicationTypeNumber == 3) {
                        scanner = new Scanner(System.in);
                        System.out.print("Введите название: ");
                        String title = scanner.nextLine();
                        System.out.print("Введите автора: ");
                        String author = scanner.nextLine();
                        System.out.print("Введите год: ");
                        int year = scanner.nextInt();

                        if (publicationTypeNumber == 1) {
                            System.out.print("Введите ISBN: ");
                            String ISBN = scanner.next();
                            Book book = new Book(title, author, year);
                            book.setISBN(ISBN);
                            library.addPublication(book);
                            System.out.println("---------------------------------------");
                        } else if (publicationTypeNumber == 2) {
                            System.out.print("Введите номер выпуска: ");
                            int issueNumber = scanner.nextInt();
                            Magazine magazine = new Magazine(title, author, year);
                            magazine.setIssueNumber(issueNumber);
                            library.addPublication(magazine);
                            System.out.println("---------------------------------------");
                        } else if (publicationTypeNumber == 3) {
                            System.out.print("Введите день публикации: ");
                            String publicationDay = scanner.next();
                            Newspaper newspaper = new Newspaper(title, author, year);
                            newspaper.setPublicationDay(publicationDay);
                            library.addPublication(newspaper);
                            System.out.println("---------------------------------------");
                        }

                    } else {
                        System.out.println("---------------------------------------");
                        System.out.println("Неверный ввод.");
                        System.out.println("---------------------------------------");
                    }
                    break;

                case 2:
                    System.out.println("---------------------------------------");
                    System.out.println("Список публикаций:");
                    library.listPublications();
                    System.out.println("---------------------------------------");
                    break;

                case 3:
                    System.out.println("----------------------------------");
                    System.out.print("Введите имя автора: ");
                    scanner = new Scanner(System.in);
                    library.searchByAuthor(scanner.nextLine());
                    System.out.println("----------------------------------");
                    break;

                case 4:
                    System.out.println("----------------------------------");
                    System.out.print("Введите имя автора: ");
                    scanner = new Scanner(System.in);
                    library.deletePublication(scanner.nextLine());
                    System.out.println("----------------------------------");
                    break;

                default:
                    System.out.println("----------------------------------");
                    System.out.println("Неккоретный ввод, попробуйте еще раз.");
                    System.out.println("----------------------------------");
            }
        }
    }
}
