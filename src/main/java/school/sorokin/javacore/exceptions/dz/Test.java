package school.sorokin.javacore.exceptions.dz;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        Library library = new Library();
        boolean isRun = true;

        while (isRun) {
            System.out.println("Меню: " +
                    "\n1 - Вывести каталог.\n" +
                    "2 - Добавить объект.\n" +
                    "3 - Выдать объект.\n" +
                    "4 - Вернуть объект.\n" +
                    "5 - Выйти из приложения.\n");
            System.out.print("Введите номер (1-5): ");
            Scanner scanner = new Scanner(System.in);
            try {
                int number = scanner.nextInt();
                System.out.println("-----------------------------------------------");
                switch (number) {

                    case 1:
                        for (Book catalog : library.getAllBooks()) {
                            System.out.println(catalog);
                        }
                        System.out.println("-----------------------------------------------");
                        break;

                    case 2:
                        scanner.nextLine();
                        System.out.print("Имя автора: ");
                        String author = scanner.nextLine();
                        System.out.print("Название книги: ");
                        String title = scanner.nextLine();
                        System.out.print("Количество экземпляров: ");
                        int availableCopies = scanner.nextInt();
                        library.addBook(title, author, availableCopies);
                        System.out.println("-----------------------------------------------");
                        break;

                    case 3:
                        scanner.nextLine();
                        System.out.print("Название книги: ");
                        title = scanner.nextLine();
                        library.takeBook(title);
                        System.out.println("-----------------------------------------------");
                        break;

                    case 4:
                        scanner.nextLine();
                        System.out.print("Название книги: ");
                        title = scanner.nextLine();
                        library.returnBook(title);
                        System.out.println("-----------------------------------------------");
                        break;

                    case 5:
                        isRun = false;
                        System.out.println("Выход из программы.");
                        System.out.println("-----------------------------------------------");
                        break;

                    default:
                        System.out.println("Введите корректное число (1-5)");
                        System.out.println("-----------------------------------------------");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: Введите целое число.");
                System.out.println("-----------------------------------------------");
            } catch (NullPointerException e) {
                System.out.println(e);
                System.out.println("-----------------------------------------------");
            } catch (NoAvailableCopiesException e) {
                System.out.println(e.getMessage());
                System.out.println("-----------------------------------------------");
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
                System.out.println("-----------------------------------------------");
            }

            if (isRun == false) {
                scanner.close();
            }
        }

    }
}
