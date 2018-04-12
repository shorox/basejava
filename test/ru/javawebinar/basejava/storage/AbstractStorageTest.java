package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public abstract class AbstractStorageTest {

    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    private static final String FULL_NAME1 = "name_1";
    private static final String FULL_NAME2 = "name_2";
    private static final String FULL_NAME3 = "name_3";

    private static Resume RESUME_1 = new Resume(UUID_1, FULL_NAME1);
    private static Resume RESUME_2 = new Resume(UUID_2, FULL_NAME2);
    private static Resume RESUME_3 = new Resume(UUID_3, FULL_NAME3);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void doSaveTest() {
        Resume resumeSave = new Resume("uuid", "name");
        storage.save(resumeSave);
        assertEquals(4, storage.size());
        assertTrue(storage.get("uuid") == resumeSave);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistTest() {
        storage.save(RESUME_1);
    }

    @Test
    public void doGetTest() {
        assertEquals(storage.get(UUID_1), RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistTest() {
        storage.get("dummy");
    }

    @Test
    public void doUpdateTest() {
        Resume resumeUpdate = new Resume(UUID_1, FULL_NAME2);
        storage.update(resumeUpdate);
        assertTrue(storage.get(UUID_1).equals(resumeUpdate));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistTest() {
        storage.update(new Resume("dummy"));
    }

    @Test(expected = NotExistStorageException.class)
    public void doDeleteTest() {
        storage.delete(UUID_3);
        assertEquals(2, storage.size());
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistTest() {
        storage.delete("dummy");
    }

    @Test
    public void sizeTest() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clearTest() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAllSortedTest() {
        assertTrue(new ArrayList<>(Arrays.asList(RESUME_1, RESUME_2, RESUME_3)).equals(storage.getAllSorted()));
    }
}