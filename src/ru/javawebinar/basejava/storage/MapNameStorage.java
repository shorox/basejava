package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Map;

public class MapNameStorage extends MapStorage {

    public String searchByName(String fullName) {
        for (Map.Entry<String, Resume> entry : mapStorage.entrySet()) {
            if (entry.getValue().getFullName().equals(fullName)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
