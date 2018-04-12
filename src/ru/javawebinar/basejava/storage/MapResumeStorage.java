package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected Resume getIndex(String uuid) {
        return mapStorage.get(uuid);
    }

    @Override
    protected boolean checkIndex(Object index) {
        return index != null;
    }

    @Override
    protected Stream<Resume> getStream() {
        return mapStorage.values().stream();
    }

    @Override
    protected void doSave(Resume resume, Object index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object index) {
        return (Resume) index;
    }

    @Override
    protected void doUpdate(Resume resume, Object index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Object index) {
        mapStorage.remove(((Resume) index).getUuid());
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
