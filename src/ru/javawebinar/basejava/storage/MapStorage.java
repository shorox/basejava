package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MapStorage extends AbstractStorage<String> {

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected String getIndex(String uuid) {
        return uuid;
    }

    @Override
    protected boolean checkIndex(String index) {
        return mapStorage.containsKey(index);
    }

    @Override
    protected Stream<Resume> getStream() {
        return mapStorage.values().stream();
    }

    @Override
    protected void doSave(Resume resume, String index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(String index) {
        return mapStorage.get(index);
    }

    @Override
    protected void doUpdate(Resume resume, String index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(String index) {
        mapStorage.remove(index);
    }

    @Override
    public int size() {
        return mapStorage.size();
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }
}
