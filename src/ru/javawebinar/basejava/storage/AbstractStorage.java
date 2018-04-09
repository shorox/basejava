package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Objects;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getIndex(String fullName);

    protected abstract boolean checkIndex(Object index);

    protected abstract void doSave(Resume resume, Object index);

    protected abstract Resume doGet(Object index);

    protected abstract void doUpdate(Resume resume, Object index);

    protected abstract void doDelete(Object index);

    @Override
    public void save(Resume resume) {
        Objects.requireNonNull(resume, "Bad news, we received null for save!");
        Objects.requireNonNull(resume.getFullName(), "Bad news, we can't save null input!");

        Object index = getNotExistedIndex(resume.getFullName());
        doSave(resume, index);
    }

    @Override
    public Resume get(String fullName) {
        Object index = getExistedIndex(fullName);
        return doGet(index);
    }

    @Override
    public void update(Resume resume) {
        Objects.requireNonNull(resume, "Bad news, we received null for update!");

        Object index = getExistedIndex(resume.getFullName());
        doUpdate(resume, index);
    }

    @Override
    public void delete(String fullName) {
        Object index = getExistedIndex(fullName);
        doDelete(index);
    }

    private Object getExistedIndex(String fullName) {
        Object index = getIndex(fullName);
        if (!checkIndex(index)) {
            throw new NotExistStorageException(fullName);
        }
        return index;
    }

    private Object getNotExistedIndex(String fullName) {
        Object index = getIndex(fullName);
        if (checkIndex(index)) {
            throw new ExistStorageException(fullName);
        }
        return index;
    }
}
