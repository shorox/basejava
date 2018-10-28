package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;
    private String fullName;
    private String imagePath;
    private String realSavePath;

    private final Map<ContactsType, String> contacts = new EnumMap<>(ContactsType.class);
    private final Map<SectionType, Category> sections = new EnumMap<>(SectionType.class);

    public Resume() {
    }

    public Resume(String fullName, String imagePath,String realSavePath) {
        this(UUID.randomUUID().toString(), fullName, imagePath,realSavePath);
    }

    public Resume(String uuid, String fullName, String imagePath,String realSavePath) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        Objects.requireNonNull(imagePath, "imagePath path must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
        this.imagePath = imagePath;
        this.realSavePath = realSavePath;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getRealSavePath() {
        return realSavePath;
    }

    public void setRealSavePath(String realSavePath) {
        this.realSavePath = realSavePath;
    }

    public Category getSections(SectionType type) {
        return sections.get(type);
    }

    public String getContacts(ContactsType type) {
        return contacts.get(type);
    }

    public void addContact(ContactsType type, String value) {
        contacts.put(type, value);
    }

    public void addCategory(SectionType type, Category section) {
        sections.put(type, section);
    }

    public Map<ContactsType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, Category> getSections() {
        return sections;
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }
}