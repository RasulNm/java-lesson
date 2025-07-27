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

                // Просмотр всех контактов
                case 2:
                    System.out.print("Список контаков: ");
                    for (int i = 0; i < names.length && i < phoneNumbers.length; i++) {
                        if (names[i] == null && phoneNumbers[i] == null) {
                            break;
                        } else {
                            System.out.print("\n" + (i + 1) + ". " + names[i] + " - " + phoneNumbers[i]);
                        }
                    }
                    if (names[0] == null && phoneNumbers[0] == null) {
                        System.out.print("0");
                    }
                    System.out.println("\n----------------------------------");
                    break;

                // Поиск контакта по имени
                case 3:
                    System.out.print("Введите имя для поиска: ");
                    String searcheByName = scanner.next();
                    String[] listOfNames = new String[names.length];
                    for (int i = 0, j = 0; i < names.length && i < phoneNumbers.length; i++) {
                        if (searcheByName.equals(names[i])) {
                            listOfNames[j] = names[i] + ": " + phoneNumbers[i];
                            System.out.println("Телефон " + listOfNames[j]);
                            j++;
                        }
                    }
                    if (listOfNames[0] == null) {
                        System.out.println("Контакт с именем " + searcheByName + " не найден.");
                    }
                    System.out.println("----------------------------------");
                    break;

                // Удаление контакта (использована пузырьковая сортировка, чтобы сдвинуть элементы для заполнения пустого места)
                case 4:
                    System.out.print("Введите имя для удаления: ");
                    String nameToDelete = scanner.next();
                    String temp;
                    for (int i = 0; i < names.length && i < phoneNumbers.length; i++) {
                        if (nameToDelete.equals(names[i])) {
                            names[i] = null;
                            phoneNumbers[i] = null;

                            for (int j = 0; j < names.length - 1; j++) {
                                if (names[j] == null) {
                                    temp = names[j];
                                    names[j] = names[j + 1];
                                    names[j + 1] = temp;
                                }
                                if (phoneNumbers[j] == null) {
                                    temp = phoneNumbers[j];
                                    phoneNumbers[j] = phoneNumbers[j + 1];
                                    phoneNumbers[j + 1] = temp;
                                }
                                if (j == names.length - 2) {
                                    i--;
                                }
                            }

                        }
                    }
                    System.out.println("----------------------------------");
                    break;
            }
        }
        scanner.close();
    }
}