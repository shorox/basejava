package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public interface Storage {
    /**
     * @return array, contains only Resumes in ru.javawebinar.basejava.storage (without null)
     */
    Resume[] getAll();

    int size();

    void save(Resume resume);

    Resume get(String uuid);

    void update(Resume resume);

    void delete(String uuid);

    void clear();

}
