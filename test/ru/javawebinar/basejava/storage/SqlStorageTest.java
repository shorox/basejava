package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(new SqlStorage(Config.get().getUrl(), Config.get().getUser(), Config.get().getPassword()));
    }

    @Test(expected = StorageException.class)
    public void wrongCredential() {
        storage = new SqlStorage(Config.get().getUrl(), "wrong", Config.get().getPassword());
        try {
            storage.clear();
        } catch (ExistStorageException e) {
            Assert.fail();
        }
    }
}
