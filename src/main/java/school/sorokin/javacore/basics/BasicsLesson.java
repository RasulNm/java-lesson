package school.sorokin.javacore.basics;

import java.util.Scanner;

public class BasicsLesson {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] names = new String[3];
        String[] phoneNumbers = new String[names.length];
        boolean isExitFromCycle = true;

        while (isExitFromCycle) {

            System.out.println("Выберите номер функции управления контактами: " +
                    "\n1. Добавление нового контакта. \n2. Просмотр всех контактов." +
                    "\n3. Поиск контакта по имени. \n4. Удаление контакта." +
                    "\n5. Выход из программы.");
            System.out.print("Введите число (1-5): ");
            int functionNumber = scanner.nextInt();
            System.out.println("----------------------------------");

            switch (functionNumber) {

                // Добавление нового контакта
                case 1:
                    int count = 0;
                    for (int i = 0; i < names.length && i < phoneNumbers.length; i++, count++) {
                        if (names[i] == null && phoneNumbers[i] == null) {
                            System.out.print("Имя: ");
                            names[i] = scanner.next();
                            System.out.print("Телефон: ");
                            phoneNumbers[i] = scanner.next();
                            break;
                        }
                    }
                    if (count >= names.length) {
                        System.out.println("Список контактов полностью заполнен.");
                    }
                    System.out.println("----------------------------------");
                    break;
            }
        }
        scanner.close();
    }
}