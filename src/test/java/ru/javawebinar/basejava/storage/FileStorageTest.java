package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategy.PathStrategy;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR,new PathStrategy()));
    }
}