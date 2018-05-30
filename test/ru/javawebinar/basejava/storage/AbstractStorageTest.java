package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    protected Storage storage;

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();

    private static final Resume R1;
    private static final Resume R2;
    private static final Resume R3;
    private static final Resume R4;

    static {
        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");

        R1.addContact(ContactsType.MAIL, "mail1@ya.ru");
        R1.addContact(ContactsType.PHONE, "11111");
//        R1.addCategory(SectionType.OBJECTIVE, new StringCategory("Objective1"));
//        R1.addCategory(SectionType.PERSONAL, new StringCategory("Personal data"));
//        R1.addCategory(SectionType.ACHIEVEMENT, new ListCategory("Achivment11", "Achivment12", "Achivment13"));
//        R1.addCategory(SectionType.QUALIFICATIONS, new ListCategory("Java", "SQL", "JavaScript"));
//        R1.addCategory(SectionType.EXPERIENCE,
//                new OrganizationsCategory(
//                        new Organization("Organization11", null,
//                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
//                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
//        R1.addCategory(SectionType.EDUCATION,
//                new OrganizationsCategory(
//                        new Organization("Institute", "http://Organization11.ru",
//                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
//                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet"))));
        R2.addContact(ContactsType.MAIL, "mail@ya.ru");
        R2.addContact(ContactsType.PHONE, "111");
//        R2.addCategory(SectionType.OBJECTIVE, new StringCategory("Objective2"));
//        R2.addCategory(SectionType.PERSONAL, new StringCategory("Personal data2"));
//        R2.addCategory(SectionType.ACHIEVEMENT, new ListCategory("Achivment112", "Achivment122", "Achivment132"));
//        R2.addCategory(SectionType.QUALIFICATIONS, new ListCategory("Java", "SQL", "JavaScript"));
//        R2.addCategory(SectionType.EXPERIENCE,
//                new OrganizationsCategory(
//                        new Organization("Organization11", "http://Organization11.ru",
//                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
//                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
//        R2.addCategory(SectionType.EDUCATION,
//                new OrganizationsCategory(
//                        new Organization("Institute", "http://Organization121.ru",
//                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
//                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet"))));
        R3.addContact(ContactsType.MAIL, "mail13@ya.ru");
        R3.addContact(ContactsType.PHONE, "111113");
//        R3.addCategory(SectionType.OBJECTIVE, new StringCategory("Objective13"));
//        R3.addCategory(SectionType.PERSONAL, new StringCategory("Personal data3"));
//        R3.addCategory(SectionType.ACHIEVEMENT, new ListCategory("Achivment113", "Achivment123", "Achivment133"));
//        R3.addCategory(SectionType.QUALIFICATIONS, new ListCategory("Java", "SQL", "JavaScript"));
//        R3.addCategory(SectionType.EXPERIENCE,
//                new OrganizationsCategory(
//                        new Organization("Organization11", "http://Organization11.ru",
//                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
//                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
//        R3.addCategory(SectionType.EDUCATION,
//                new OrganizationsCategory(
//                        new Organization("Institute", "http://Organization11.ru",
//                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
//                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet"))));
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(R1, R2, R3));
    }

    @Test
    public void save() {
        storage.save(R4);
        assertSize(4);
        assertGet(R4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(R1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void get() {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}