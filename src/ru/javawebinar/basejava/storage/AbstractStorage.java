package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Objects;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getIndex(String uuid);

    protected abstract boolean checkIndex(Object index);

    protected abstract void doSave(Resume resume, Object index);

    protected abstract Resume doGet(Object index);

    protected abstract void doUpdate(Resume resume, Object index);

    protected abstract void doDelete(Object index);

    @Override
    public void save(Resume resume) {
        Objects.requireNonNull(resume, "Bad news, we received null for save!");
        Objects.requireNonNull(resume.getUuid(), "Bad news, we can't save null input!");

        Object index = getIndex(resume.getUuid());
        if (checkIndex(index)) {
            throw new ExistStorageException(resume.getUuid());
        }
        doSave(resume, index);
    }

    @Override
    public Resume get(String uuid) {

        Object indexGet = getIndex(uuid);
        if (checkIndex(indexGet)) {
            return doGet(indexGet);
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void update(Resume resume) {
        Objects.requireNonNull(resume, "Bad news, we received null for update!");

        Object index = getIndex(resume.getUuid());
        if (checkIndex(index)) {
            doUpdate(resume, index);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void delete(String uuid) {

        Object index = getIndex(uuid);
        if (checkIndex(index)) {
            doDelete(index);
            return;
        }
        throw new NotExistStorageException(uuid);
    }
}
