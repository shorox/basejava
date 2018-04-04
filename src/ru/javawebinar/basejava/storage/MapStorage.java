package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    protected Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected int getIndex(String uuid) {
        if (mapStorage.containsKey(uuid)) {
            return 1;
        }
        return -1;
    }

    @Override
    protected void doSave(Resume resume, int index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(String uuid, int index) {
        return mapStorage.get(uuid);
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(String uuid, int index) {
        mapStorage.remove(uuid);
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
        return mapStorage.values().stream().toArray(Resume[]::new);
    }
}
