package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategy.DataStrategy;

public class DataStorageTest extends AbstractStorageTest {

    public DataStorageTest() {
        super(new FileStorage(STORAGE_DIR, new DataStrategy()));
    }
}