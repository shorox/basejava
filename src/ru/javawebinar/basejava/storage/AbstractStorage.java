package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getIndex(String uuid);

    protected abstract boolean checkIndex(Object index);

    protected abstract Stream<Resume> getStream();

    protected abstract void doSave(Resume resume, Object index);

    protected abstract Resume doGet(Object index);

    protected abstract void doUpdate(Resume resume, Object index);

    protected abstract void doDelete(Object index);

    @Override
    public void save(Resume resume) {
        Objects.requireNonNull(resume, "Bad news, we received null for save!");
        Objects.requireNonNull(resume.getUuid(), "Bad news, we can't save null input!");

        Object index = getNotExistedIndex(resume.getUuid());
        doSave(resume, index);
    }

    @Override
    public Resume get(String uuid) {
        Object index = getExistedIndex(uuid);
        return doGet(index);
    }

    @Override
    public void update(Resume resume) {
        Objects.requireNonNull(resume, "Bad news, we received null for update!");

        Object index = getExistedIndex(resume.getUuid());
        doUpdate(resume, index);
    }

    @Override
    public void delete(String uuid) {
        Object index = getExistedIndex(uuid);
        doDelete(index);
    }

    @Override
    public List<Resume> getAllSorted() {
        return getStream().sorted(Comparator.comparing(Resume::getFullName)).collect(Collectors.toList());
    }

    private Object getExistedIndex(String uuid) {
        Object index = getIndex(uuid);
        if (!checkIndex(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    private Object getNotExistedIndex(String uuid) {
        Object index = getIndex(uuid);
        if (checkIndex(index)) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }
}
