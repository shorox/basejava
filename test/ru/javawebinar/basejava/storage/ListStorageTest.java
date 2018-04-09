package ru.javawebinar.basejava.storage;

import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListStorageTest extends AbstractStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Test
    public void checkIndexTest() {
        assertEquals((Integer) 0, getIndex("name_1"));
    }

    public Integer getIndex(String fullName) {
        List<Resume> list = new ArrayList<>(Arrays.asList(
                new Resume("name_1"),
                new Resume("name_2"),
                new Resume("name_3")));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFullName().equals(fullName)) {
                return i;
            }
        }
        return null;
    }
}