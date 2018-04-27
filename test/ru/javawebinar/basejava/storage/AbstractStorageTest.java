package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LL/yyyy");
        Organization expOrg1 = new Organization(
                new Link("Работа 1", "http://javaops.ru/reg/basejava/1"),
                new ArrayList<>(Arrays.asList((new Position(LocalDate.of(2006, Month.APRIL, 10),
                                LocalDate.of(2009, Month.SEPTEMBER, 05), "position1", "duties1")),
                        (new Position(LocalDate.of(2007, Month.APRIL, 10),
                                LocalDate.of(2008, Month.SEPTEMBER, 05), "position1", "duties1")))));
        Organization expOrg2 = new Organization(
                new Link("Работа 2", "http://12345"),
                new ArrayList<>(Arrays.asList(new Position(LocalDate.of(2006, Month.APRIL, 10),
                        LocalDate.of(2009, Month.SEPTEMBER, 05), "position1", "duties1"))));
        List<Organization> listExp = new ArrayList<>(Arrays.asList(expOrg1, expOrg2));
        Organization eduOrg1 = new Organization(
                new Link("Учеба 1", "http://javaops.ru/1"),
                new ArrayList<>(Arrays.asList(new Position(LocalDate.of(2003, Month.APRIL, 10),
                        LocalDate.of(2008, Month.SEPTEMBER, 05), "студент"))));
        Organization eduOrg2 = new Organization(
                new Link("Учеба 2", "http://javaops.ru/2"),
                new ArrayList<>(Arrays.asList(new Position(LocalDate.of(2016, Month.APRIL, 10),
                        LocalDate.of(2018, Month.SEPTEMBER, 05), "студент заочно"))));
        List<Organization> listEdu = new ArrayList<>(Arrays.asList(eduOrg1, eduOrg2));

        RESUME_1.getContacts().put(ContactsType.PHONE, "+3801235467");
        RESUME_1.getContacts().put(ContactsType.SKYPE, "gagarina6794");
        RESUME_1.getSections().put(SectionType.OBJECTIVE, new StringCategory("Junior Java Developer"));
        RESUME_1.getSections().put(SectionType.PERSONAL, new StringCategory("Аналитический склад ума"));
        RESUME_1.getSections().put(SectionType.ACHIEVEMENT, new ListCategory(new ArrayList<>(Arrays.asList("Пока только бухгалтерские", "и спортивные)"))));
        RESUME_1.getSections().put(SectionType.QUALIFICATIONS, new ListCategory(new ArrayList<>(Arrays.asList("Java junior", "Bookkeeper"))));
        RESUME_1.getSections().put(SectionType.EXPERIENCE, new OrganizationsCategory(listExp));
        RESUME_1.getSections().put(SectionType.EDUCATION, new OrganizationsCategory(listEdu));

        RESUME_2.getContacts().put(ContactsType.PHONE, "+3801235467");
        RESUME_2.getContacts().put(ContactsType.SKYPE, "gagarina6794");
        RESUME_2.getSections().put(SectionType.OBJECTIVE, new StringCategory("Junior Java Developer"));
        RESUME_2.getSections().put(SectionType.PERSONAL, new StringCategory("Аналитический склад ума"));
        RESUME_2.getSections().put(SectionType.ACHIEVEMENT, new ListCategory(new ArrayList<>(Arrays.asList("Пока только бухгалтерские", "и спортивные)"))));
        RESUME_2.getSections().put(SectionType.QUALIFICATIONS, new ListCategory(new ArrayList<>(Arrays.asList("Java junior", "Bookkeeper"))));
        RESUME_2.getSections().put(SectionType.EXPERIENCE, new OrganizationsCategory(listExp));
        RESUME_2.getSections().put(SectionType.EDUCATION, new OrganizationsCategory(listEdu));

        RESUME_3.getContacts().put(ContactsType.PHONE, "+3801235467");
        RESUME_3.getContacts().put(ContactsType.SKYPE, "gagarina6794");
        RESUME_3.getSections().put(SectionType.OBJECTIVE, new StringCategory("Junior Java Developer"));
        RESUME_3.getSections().put(SectionType.PERSONAL, new StringCategory("Аналитический склад ума"));
        RESUME_3.getSections().put(SectionType.ACHIEVEMENT, new ListCategory(new ArrayList<>(Arrays.asList("Пока только бухгалтерские", "и спортивные)"))));
        RESUME_3.getSections().put(SectionType.QUALIFICATIONS, new ListCategory(new ArrayList<>(Arrays.asList("Java junior", "Bookkeeper"))));
        RESUME_3.getSections().put(SectionType.EXPERIENCE, new OrganizationsCategory(listExp));
        RESUME_3.getSections().put(SectionType.EDUCATION, new OrganizationsCategory(listEdu));
    }

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