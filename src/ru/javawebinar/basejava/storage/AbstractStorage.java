package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Objects;

public abstract class AbstractStorage implements Storage {

    protected abstract int getIndex(String uuid);

    protected abstract void doSave(Resume resume, int index);

    protected abstract Resume doGet(String uuid, int index);

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract void doDelete(String uuid, int index);

    @Override
    public void save(Resume resume) {
        Objects.requireNonNull(resume, "Bad news, we received null for save!");
        Objects.requireNonNull(resume.getUuid(), "Bad news, we can't save null input!");

        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        doSave(resume, index);
    }

    @Override
    public Resume get(String uuid) {
        int indexGet = getIndex(uuid);
        if (indexGet >= 0) {
            return doGet(uuid, indexGet);
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void update(Resume resume) {
        Objects.requireNonNull(resume, "Bad news, we received null for update!");

        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            doUpdate(resume, index);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            doDelete(uuid, index);
            return;
        }
        throw new NotExistStorageException(uuid);
    }
}
