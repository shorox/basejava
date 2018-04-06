package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    protected Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected Object getIndex(String uuid) {
        return uuid;
    }

    @Override
    protected boolean checkIndex(Object index) {
        if (mapStorage.containsKey(index)) {
            return true;
        }
        return false;
    }

    @Override
    protected void doSave(Resume resume, Object index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object index) {
        return mapStorage.get((String) index);
    }

    @Override
    protected void doUpdate(Resume resume, Object index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Object index) {
        mapStorage.remove((String) index);
    }

    @Override
    public int size() {
        return mapStorage.size();
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public Resume[] getAll() {
        return mapStorage.values().toArray(new Resume[mapStorage.size()]);
    }
}
