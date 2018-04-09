package ru.javawebinar.basejava.storage;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    public Integer getIndex(String fullName) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].getFullName().equals(fullName)) {
                return i;
            }
        }
        return -1;
    }
}