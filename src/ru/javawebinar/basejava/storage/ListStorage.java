package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected List<Resume> listStorage = new ArrayList<>();

    @Override
    protected Object getIndex(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean checkIndex(Object index) {
        return (int) index >= 0;
    }

    @Override
    protected void doSave(Resume resume, Object index) {
        listStorage.add(resume);
    }

    @Override
    protected Resume doGet(Object index) {
        return listStorage.get((Integer) index);
    }

    @Override
    protected void doUpdate(Resume resume, Object index) {
        listStorage.set((Integer) index, resume);
    }

    @Override
    protected void doDelete(Object index) {
        listStorage.remove((int) index);
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
        return listStorage.toArray(new Resume[listStorage.size()]);
    }
}
