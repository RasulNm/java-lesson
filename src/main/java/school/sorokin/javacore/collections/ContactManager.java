package school.sorokin.javacore.collections;

import java.util.*;

public class ContactManager {
    private List<Contact> listContacts;
    private Set<Contact> setContacts;
    private Map<String, Contact> mapContacts;

    // добавление контакта
    public void addContact(Contact contact, Contact mapContact) {
        if (listContacts == null) {
            listContacts = new ArrayList<>();
            setContacts = new HashSet<>();
            mapContacts = new HashMap<>();
        }
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
        if (listContacts == null || listContacts.size() == 0) {
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

}
