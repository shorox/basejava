package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected String getIndex(String fullName) {
        return fullName;
    }

    @Override
    protected boolean checkIndex(Object index) {
        return mapStorage.containsKey(index);
    }

    @Override
    protected void doSave(Resume resume, Object index) {
        mapStorage.put(resume.getFullName(), resume);
    }

    @Override
    protected Resume doGet(Object index) {
        return mapStorage.get(index);
    }

    @Override
    protected void doUpdate(Resume resume, Object index) {
        mapStorage.put(resume.getFullName(), resume);
    }

    @Override
    protected void doDelete(Object index) {
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

    @Override
    public List<Resume> getAllSorted() {
        return mapStorage.values().stream().sorted(Comparator.comparing(Resume::getFullName)).collect(Collectors.toList());
    }
}
