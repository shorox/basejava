package storage;

import model.Resume;

import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    protected abstract int getIndex(String uuid);

    protected abstract void doSave(Resume resume);

    protected abstract void deleteByIndex(int index);

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        Objects.requireNonNull(resume, "Bad news, we received null for update!");

        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Sorry, Resume was not found for update!");
        }
    }

    public void save(Resume resume) {
        Objects.requireNonNull(resume, "Bad news, we received null for save!");

        if (resume.getUuid() == null) {
            System.out.println("Sorry, we can't save you null input.");
            return;
        }

        if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("Can't save! This Resume is already exist!");
            return;
        }

        if (size >= STORAGE_LIMIT) {
            System.out.println("Sorry, free space is out for this save!");
            return;
        }

        doSave(resume);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            size--;
            deleteByIndex(index);
            storage[size] = null;
            return;
        }
        System.out.println("Resume with : " + uuid + " was not found for delete!");
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("Sorry, Resume with " + uuid + " was not found! Please, check your input data.");
        return null;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    //Method works in pair with save method, return null if input data correct
    private String checkSaveConditions(Resume resume) {
        Objects.requireNonNull(resume, "Bad news, we received null for save!");

        if (resume.getUuid() == null) {
            return "Sorry, we can't save you null input.";
        }

        if (getIndex(resume.getUuid()) >= 0) {
            return "Can't save! This Resume is already exist!";
        }

        if (size >= STORAGE_LIMIT) {
            return "Sorry, free space is out for this save!";
        }
        return null;
    }
}
