package ru.javawebinar.basejava.model;

import java.util.*;

public class Resume implements Comparable<Resume> {

    private final String uuid;
    private String fullName;

    private EnumMap<ContactsType, String> contacts = new EnumMap<>(ContactsType.class);
    private EnumMap<SectionType, Category> sections = new EnumMap<>(SectionType.class);

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public EnumMap<SectionType, Category> getSections() { return sections; }

    public EnumMap<ContactsType, String> getContacts() {
        return contacts;
    }

    public void addContact(ContactsType type, String value) {
        contacts.put(type, value);
    }

    public void addCategory(SectionType type, Category section) {
        sections.put(type, section);
    }

    @Override
    public String toString() {
        return fullName + " : " + uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) &&
                fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }
}
