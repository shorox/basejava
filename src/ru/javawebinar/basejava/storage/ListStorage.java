package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected List<Resume> listStorage = new ArrayList<>();

    @Override
    protected int getIndex(String uuid) {
        for (Resume resume : listStorage) {
            if (resume.getUuid().equals(uuid)) {
                return listStorage.indexOf(resume);
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Resume resume, int index) {
        listStorage.add(resume);
    }

    @Override
    protected Resume doGet(String uuid, int index) {
        return listStorage.get(index);
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        listStorage.set(index, resume);
    }

    @Override
    protected void doDelete(String uuid, int index) {
        listStorage.remove(index);
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public Resume[] getAll() {
        return listStorage.stream().toArray(Resume[]::new);
    }
}
