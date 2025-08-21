package school.sorokin.javacore.collections;

import java.util.*;

public class ContactManager {
    private List<Contact> listContacts;
    private Set<Contact> setContacts;
    private Map<String, Contact> mapContacts;

    public ContactManager(){
        listContacts = new ArrayList<>();
        setContacts = new HashSet<>();
        mapContacts = new HashMap<>();
    }

    // добавление контакта
    public void addContact(Contact contact, Contact mapContact) {
        setContacts.add(contact);
        if (setContacts.size() > listContacts.size()) {
            listContacts.add(contact);
            mapContacts.put(contact.getGroup(), mapContact);
            System.out.println("Контакт добавлен.");
        } else {
            System.out.println("Контакт с таким именем и номером уже существует!");
        }
    }

    // удаление контакта
    public String deleteContact(String name) throws NullPointerException {
        if (listContacts.size() == 0) {
            throw new NullPointerException("Пусто. Контакты не добавлены.");
        }

        String message = "Контакт с таким именем не найден.";

        Iterator<Contact> listIterator = listContacts.iterator();
        while (listIterator.hasNext()) {
            if (listIterator.next().getName().equals(name)) {
                listIterator.remove();
                message = "Контакт удален.";
            }
        }

        Iterator<Contact> setIterator = setContacts.iterator();
        while (setIterator.hasNext()) {
            if (setIterator.next().getName().equals(name)) {
                setIterator.remove();
            }
        }

        Iterator<Map.Entry<String, Contact>> mapIterator = mapContacts.entrySet().iterator();
        while (mapIterator.hasNext()) {
            if (mapIterator.next().getValue().getName().equals(name)) {
                mapIterator.remove();
            }
        }
        return message;
    }

    // просмотр всех контактов
    public void getAllContacts() throws NullPointerException {
        if (listContacts.size() == 0) {
            throw new NullPointerException("Пусто. Контакты не добавлены.");
        }
        Iterator<Contact> iterator = listContacts.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    // поиск контакта по имени
    public void getContactByName(String name) throws NullPointerException {
        if (listContacts.size() == 0) {
            throw new NullPointerException("Пусто. Контакты не добавлены.");
        }
        Iterator<Contact> listIterator = listContacts.iterator();
        while (listIterator.hasNext()) {
            Contact contact = listIterator.next();
            if (contact.getName().equals(name)) {
                System.out.println(contact);
            }
        }
    }

    // просмотр контактов по группе (при добавлении элемента в Map,
    // если ключ уже существует, то старое значение будет перезаписано новым значением,
    // а ключ останется неизменным)
    public void getContactsByGroup(String group) {
        if (listContacts.size() == 0) {
            throw new NullPointerException("Пусто. Контакты не добавлены.");
        }
        for (Map.Entry<String, Contact> entry : mapContacts.entrySet()) {
            if (entry.getKey().equals(group)) {
                System.out.println("---Контакты в группе \"" + entry.getKey() + "\":---");
                System.out.println(entry.getValue().getName() + " | " + entry.getValue().getPhone()
                        + " | " + entry.getValue().getEmail());
            }
        }
    }
}
