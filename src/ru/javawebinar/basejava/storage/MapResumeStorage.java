package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected Resume getIndex(String uuid) {
        return mapStorage.get(uuid);
    }

    @Override
    protected boolean checkIndex(Resume index) {
        return index != null;
    }

    @Override
    protected Stream<Resume> getStream() {
        return mapStorage.values().stream();
    }

    @Override
    protected void doSave(Resume resume, Resume index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Resume index) {
        return index;
    }

    @Override
    protected void doUpdate(Resume resume, Resume index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Resume index) {
        mapStorage.remove((index).getUuid());
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
