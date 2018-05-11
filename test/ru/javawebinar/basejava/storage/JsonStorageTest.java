package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategy.JsonStrategy;

public class JsonStorageTest extends AbstractStorageTest {

    public JsonStorageTest() {
        super(new FileStorage(STORAGE_DIR, new JsonStrategy()));
    }
}