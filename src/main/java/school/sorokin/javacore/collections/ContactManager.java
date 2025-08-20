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
}
