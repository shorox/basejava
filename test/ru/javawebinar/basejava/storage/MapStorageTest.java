package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;

public class MapStorageTest {

    private Storage storage = new MapStorage();

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void checkIndexTest() {
        assertEquals(UUID_1, getIndex("uuid1"));
    }

    @Test
    public void doSaveTest() {
        Resume resumeSave = new Resume("uuid4");
        storage.save(resumeSave);
        assertEquals(4, storage.size());
        assertTrue(storage.get("uuid4") == resumeSave);
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
        Resume resumeUpdate = new Resume(UUID_1);
        storage.update(resumeUpdate);
        assertTrue(storage.get(UUID_1) == resumeUpdate);
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
    public void getAllTest() {
        Resume[] resumeMap = storage.getAll();
        Arrays.sort(resumeMap);
        assertArrayEquals(new Resume[]{RESUME_1, RESUME_2, RESUME_3}, resumeMap);
    }

    private Object getIndex(String uuid) {
        return uuid;
    }
}