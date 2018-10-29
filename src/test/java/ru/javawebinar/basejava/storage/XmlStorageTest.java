package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategy.XmlStrategy;

public class XmlStorageTest extends AbstractStorageTest {

    public XmlStorageTest()
    {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStrategy()));
    }
}
