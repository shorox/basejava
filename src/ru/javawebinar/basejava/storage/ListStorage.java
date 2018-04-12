package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ListStorage extends AbstractStorage<Integer> {

    protected List<Resume> listStorage = new ArrayList<>();

    @Override
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean checkIndex(Integer index) {
        return index != null;
    }

    @Override
    protected Stream<Resume> getStream() {
        return listStorage.stream();
    }

    @Override
    protected void doSave(Resume resume, Integer index) {
        listStorage.add(resume);
    }

    @Override
    protected Resume doGet(Integer index) {
        return listStorage.get(index);
    }

    @Override
    protected void doUpdate(Resume resume, Integer index) {
        listStorage.set(index, resume);
    }

    @Override
    protected void doDelete(Integer index) {
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
}
