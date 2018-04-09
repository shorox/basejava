package ru.javawebinar.basejava.storage;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Test
    public void checkIndexTest() {
        assertEquals("name_1", getIndex("name_1"));
    }

    public Object getIndex(String fullName) {
        return fullName;
    }
}