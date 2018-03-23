package ru.javawebinar.storage;

import ru.javawebinar.model.Resume;

public interface Storage {

    void clear();

    void update(Resume resume);

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    /**
     * @return array, contains only Resumes in ru.javawebinar.storage (without null)
     */
    Resume[] getAll();

    int size();

}
