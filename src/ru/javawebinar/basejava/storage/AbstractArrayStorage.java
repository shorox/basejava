package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.stream.Stream;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int size;

    protected abstract void subSave(Resume resume, int index);

    protected abstract void subDelete(int index);

    @Override
    protected boolean checkIndex(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    protected Stream<Resume> getStream() {
        return Arrays.stream(Arrays.copyOf(storage, size));
    }

    @Override
    protected void doSave(Resume resume, Object index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        subSave(resume, ((int) index));
        size++;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected void doUpdate(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    @Override
    protected void doDelete(Object index) {
        size--;
        subDelete((Integer) index);
        storage[size] = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }
}
