package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public abstract class AbstractStorageTest {

    protected Storage storage;

    @Test
    protected abstract void checkIndexTest();

    protected abstract Object getIndex(String fullName);

    private static final String FULL_NAME1 = "name_1";
    private static final String FULL_NAME2 = "name_2";
    private static final String FULL_NAME3 = "name_3";

    private static Resume RESUME_1 = new Resume(FULL_NAME1);
    private static Resume RESUME_2 = new Resume(FULL_NAME2);
    private static Resume RESUME_3 = new Resume(FULL_NAME3);

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
        Resume resumeSave = new Resume("name");
        storage.save(resumeSave);
        assertEquals(4, storage.size());
        assertTrue(storage.get("name") == resumeSave);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistTest() {
        storage.save(RESUME_1);
    }

    @Test
    public void doGetTest() {
        assertEquals(storage.get(FULL_NAME1), RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistTest() {
        storage.get("dummy");
    }

    @Test
    public void doUpdateTest() {
        Resume resumeUpdate = new Resume(FULL_NAME1);
        storage.update(resumeUpdate);
        assertTrue(storage.get(FULL_NAME1) == resumeUpdate);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistTest() {
        storage.update(new Resume("dummy"));
    }

    @Test(expected = NotExistStorageException.class)
    public void doDeleteTest() {
        storage.delete(FULL_NAME3);
        assertEquals(2, storage.size());
        storage.get(FULL_NAME3);
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
        List<Resume> list = new ArrayList<>(Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
        assertTrue(list.equals(storage.getAllSorted()));
    }
}