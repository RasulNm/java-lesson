package school.sorokin.javacore.exceptions.dz;

import java.util.InputMismatchException;
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
            }

            if (isRun == false) {
                scanner.close();
            }
        }

    }
}
