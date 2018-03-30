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

    private static final Resume RESUME_1 = new Resume(UUID_1);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUpTest() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void sizeTest() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clearTest() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void updateTest() {
        Resume resumeUpdate = new Resume(UUID_1);
        storage.update(resumeUpdate);
        Assert.assertTrue(storage.get(UUID_1) == resumeUpdate);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistTest() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void saveTest() {
        Resume resumeSave = new Resume("uuid4");
        storage.save(resumeSave);
        Assert.assertEquals(4, storage.size());
        Assert.assertTrue(storage.get("uuid4") == resumeSave);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistTest() {
        storage.save(new Resume(UUID_2));
    }

    @Test(expected = StorageException.class)
    public void saveStorageExceptionTest() {
        storage.clear();
        for (int i = 0; i < 10000; i++) {
            storage.save(new Resume("uuid" + i));
        }
        storage.save(new Resume("uuid"));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteTest() {
        storage.delete(UUID_3);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistTest() {
        storage.delete("dummy");
    }

    @Test
    public void getTest() {
        Assert.assertEquals(storage.get(UUID_1), RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistTest() {
        storage.get("dummy");
    }

    @Test
    public void getAllTest() {
        Resume[] resumes = new Resume[3];
        resumes[0] = new Resume(UUID_1);
        resumes[1] = new Resume(UUID_2);
        resumes[2] = new Resume(UUID_3);
        Assert.assertArrayEquals(resumes, storage.getAll());
    }
}