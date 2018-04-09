package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getIndex(String fullName) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getFullName().equals(fullName)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void subSave(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void subDelete(int index) {
        storage[index] = storage[size];
    }
}
