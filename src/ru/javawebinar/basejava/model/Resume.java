package ru.javawebinar.basejava.model;

import java.util.UUID;

/**
 * com.urise.webapp.ru.javawebinar.basejava.model.ru.javawebinar.basejava.model.Resume class
 */
public class Resume implements Comparable<Resume> {

    private final String uuid;

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public Resume() {
        this(UUID.randomUUID().toString());
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public int compareTo(Resume o) {
        if (o.getUuid() != null) {
            return uuid.compareTo(o.uuid);
        }
        return -1;
    }
}