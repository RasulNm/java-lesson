package school.sorokin.javacore.collections;

import org.slf4j.*;

import java.util.*;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Contact contact;
        ContactManager contactManager = new ContactManager();
        boolean isRunning = true;


        while (isRunning) {
            System.out.println("Выберите действие:");
            System.out.println(" 1 - Добавить контакт\n" +
                    " 2 - Удалить контакт\n" +
                    " 3 - Посмотреть все контакты\n" +
                    " 4 - Найти контакт по имени\n" +
                    " 5 - Посмотреть контакты по группе\n" +
                    " 0 - Выход");
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            try {

                int number = scanner.nextInt();
                System.out.println("-----------------------------------------------");

                switch (number) {
                    case 1:
                        scanner = new Scanner(System.in);
                        System.out.print("Введите имя: ");
                        String name = scanner.nextLine();
                        System.out.print("Введите телефон: ");
                        String phone = scanner.nextLine();
                        System.out.print("Введите email: ");
                        String email = scanner.nextLine();
                        System.out.print("Введите группу: ");
                        String group = scanner.nextLine();

                        if (name.isBlank() || phone.isBlank() || email.isBlank() || email.isBlank() || group.isBlank()) {
                            log.error("Ошибка: значение не может содержать только пробелы или быть пустым.");
                            System.out.println("-----------------------------------------------");
                            continue;
                        }

                        contact = new Contact(name, phone, email, group);
                        contactManager.addContact(contact, new Contact(name, phone, email));
                        System.out.println("-----------------------------------------------");
                        break;

                    case 2:
                        scanner = new Scanner(System.in);
                        System.out.print("Введите имя: ");
                        name = scanner.nextLine();
                        System.out.println(contactManager.deleteContact(name));
                        System.out.println("-----------------------------------------------");
                        break;

                    case 3:
                        System.out.println("Список контактов");
                        contactManager.getAllContacts();
                        System.out.println("-----------------------------------------------");
                        break;

                    case 4:
                        scanner = new Scanner(System.in);
                        System.out.print("Введите имя: ");
                        name = scanner.nextLine();
                        contactManager.getContactByName(name);
                        System.out.println("-----------------------------------------------");
                        break;

                    case 5:
                        scanner = new Scanner(System.in);
                        System.out.print("Введите название группы: ");
                        group = scanner.nextLine();
                        contactManager.getContactsByGroup(group);
                        System.out.println("-----------------------------------------------");
                        break;

                    case 0:
                        isRunning = false;
                        System.out.println("Выход из программы.");
                        System.out.println("-----------------------------------------------");
                        break;

                    default:
                        System.out.println("Введите корректное число (0-5)");
                        System.out.println("-----------------------------------------------");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: Введите целое число.");
                System.out.println("-----------------------------------------------");
            } catch (NullPointerException e) {
                System.out.println("Ошибка: " + e);
                System.out.println("-----------------------------------------------");
            }

            if (isRunning == false) {
                scanner.close();
            }
        }
    }
}

