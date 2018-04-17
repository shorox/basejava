package ru.javawebinar.basejava.model;

import java.util.HashMap;
import java.util.Map;

public class ContactsMap {

    private final Map<String, String> contacts = new HashMap<>();

    public Map<String, String> getContacts() {
        return contacts;
    }

    public void create(String contactName, String contact) {
        contacts.put(contactName, contact);
    }

    public String read(String contactName) {
        return contacts.get(contactName);
    }

    public void update(String contactName, String contact) {
        contacts.put(contactName, contact);
    }

    public void delete(String contactName) {
        contacts.remove(contactName);
    }
}
