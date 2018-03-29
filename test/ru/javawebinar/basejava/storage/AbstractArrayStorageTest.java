package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {

    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        storage.update(new Resume(UUID_1));
    }

    @Test
    public void save() throws Exception {
        storage.save(new Resume("uuid4"));
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_3);
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void get() throws Exception {
        Assert.assertTrue(storage.get(UUID_1).equals(new Resume(UUID_1)));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resumes = new Resume[3];
        resumes[0] = new Resume(UUID_1);
        resumes[1] = new Resume(UUID_2);
        resumes[2] = new Resume(UUID_3);
        Assert.assertArrayEquals(resumes, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("dummy"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(new Resume(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }


    @Test(expected = StorageException.class)
    public void saveStorageException() throws Exception {
       for(int i = 4 ; i <= 10000 ; i++){
           storage.save(new Resume("uuid" + i));
        }
        storage.save(new Resume("uuid"));
    }
}