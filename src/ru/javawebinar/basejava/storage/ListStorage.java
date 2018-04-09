package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListStorage extends AbstractStorage {

    protected List<Resume> listStorage = new ArrayList<>();

    @Override
    protected Integer getIndex(String fullName) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getFullName().equals(fullName)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean checkIndex(Object index) {
        return index != null;
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
    public List<Resume> getAllSorted() {
        return listStorage.stream().sorted(Comparator.comparing(Resume::getFullName)).collect(Collectors.toList());
    }
}
