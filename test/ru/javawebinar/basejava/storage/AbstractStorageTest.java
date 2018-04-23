package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;
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

    {
        RESUME_1.getContacts().put(ContactsType.PHONE, "+3801235467");
        RESUME_1.getContacts().put(ContactsType.SKYPE, "gagarina6794");
        RESUME_1.getSections().put(SectionType.OBJECTIVE, new StringCategory("Junior Java Developer"));
        RESUME_1.getSections().put(SectionType.PERSONAL, new StringCategory("Аналитический склад ума"));
        RESUME_1.getSections().put(SectionType.ACHIEVEMENT, new StringListCategory());
        RESUME_1.getSections().put(SectionType.QUALIFICATIONS, new StringListCategory());
        RESUME_1.getSections().put(SectionType.EXPERIENCE, new OrganizationsMapCategory());
        RESUME_1.getSections().put(SectionType.EDUCATION, new OrganizationsMapCategory());
        ((ListCategory) RESUME_1.getSections().get(SectionType.ACHIEVEMENT)).save("Пока только бухгалтерские)");
        ((ListCategory) RESUME_1.getSections().get(SectionType.ACHIEVEMENT)).save("и спортивные)");
        ((ListCategory) RESUME_1.getSections().get(SectionType.QUALIFICATIONS)).save("Java junior");
        ((ListCategory) RESUME_1.getSections().get(SectionType.QUALIFICATIONS)).save("Bookkeeper");
        ((OrganizationsMapCategory) RESUME_1.getSections().get(SectionType.EXPERIENCE)).
                save(new Link("Работа 1", "http://javaops.ru/reg/basejava/1"),
                        new ArrayList<>(Arrays.asList(new Organizations(LocalDate.of(2006, Month.APRIL, 10),
                                LocalDate.of(2009, Month.SEPTEMBER, 05), "position1", "duties1"))));
        ((OrganizationsMapCategory) RESUME_1.getSections().get(SectionType.EXPERIENCE)).
                save(new Link("Работа 2", "http://javaops.ru/reg/basejava/2"),
                        new ArrayList<>(Arrays.asList(new Organizations(LocalDate.of(2007, Month.APRIL, 10),
                                LocalDate.of(2008, Month.SEPTEMBER, 05), "position1", "duties1"))));
        ((OrganizationsMapCategory) RESUME_1.getSections().get(SectionType.EDUCATION)).
                save(new Link("Учеба 1", "http://javaops.ru/1"),
                        new ArrayList<>(Arrays.asList(new Organizations(LocalDate.of(2003, Month.APRIL, 10),
                                LocalDate.of(2008, Month.SEPTEMBER, 05), "студент"))));
        ((OrganizationsMapCategory) RESUME_1.getSections().get(SectionType.EDUCATION)).
                save(new Link("Учеба 2", "http://javaops.ru/2"),
                        new ArrayList<>(Arrays.asList(new Organizations(LocalDate.of(2016, Month.APRIL, 10),
                                LocalDate.of(2018, Month.SEPTEMBER, 05), "студент заочно"))));

        RESUME_2.getContacts().put(ContactsType.PHONE, "+3801235467");
        RESUME_2.getContacts().put(ContactsType.SKYPE, "gagarina6794");
        RESUME_2.getSections().put(SectionType.OBJECTIVE, new StringCategory("Junior Java Developer"));
        RESUME_2.getSections().put(SectionType.PERSONAL, new StringCategory("Аналитический склад ума"));
        RESUME_2.getSections().put(SectionType.ACHIEVEMENT, new StringListCategory());
        RESUME_2.getSections().put(SectionType.QUALIFICATIONS, new StringListCategory());
        RESUME_2.getSections().put(SectionType.EXPERIENCE, new OrganizationsMapCategory());
        RESUME_2.getSections().put(SectionType.EDUCATION, new OrganizationsMapCategory());
        ((ListCategory) RESUME_2.getSections().get(SectionType.ACHIEVEMENT)).save("Пока только бухгалтерские)");
        ((ListCategory) RESUME_2.getSections().get(SectionType.ACHIEVEMENT)).save("и спортивные)");
        ((ListCategory) RESUME_2.getSections().get(SectionType.QUALIFICATIONS)).save("Java junior");
        ((ListCategory) RESUME_2.getSections().get(SectionType.QUALIFICATIONS)).save("Bookkeeper");
        ((OrganizationsMapCategory) RESUME_2.getSections().get(SectionType.EXPERIENCE)).
                save(new Link("Работа 1", "http://javaops.ru/reg/basejava/1"),
                        new ArrayList<>(Arrays.asList(new Organizations(LocalDate.of(2006, Month.APRIL, 10),
                                LocalDate.of(2009, Month.SEPTEMBER, 05), "position1", "duties1"))));
        ((OrganizationsMapCategory) RESUME_2.getSections().get(SectionType.EXPERIENCE)).
                save(new Link("Работа 2", "http://javaops.ru/reg/basejava/2"),
                        new ArrayList<>(Arrays.asList(new Organizations(LocalDate.of(2007, Month.APRIL, 10),
                                LocalDate.of(2008, Month.SEPTEMBER, 05), "position1", "duties1"))));
        ((OrganizationsMapCategory) RESUME_2.getSections().get(SectionType.EDUCATION)).
                save(new Link("Учеба 1", "http://javaops.ru/1"),
                        new ArrayList<>(Arrays.asList(new Organizations(LocalDate.of(2003, Month.APRIL, 10),
                                LocalDate.of(2008, Month.SEPTEMBER, 05), "студент"))));
        ((OrganizationsMapCategory) RESUME_2.getSections().get(SectionType.EDUCATION)).
                save(new Link("Учеба 2", "http://javaops.ru/2"),
                        new ArrayList<>(Arrays.asList(new Organizations(LocalDate.of(2016, Month.APRIL, 10),
                                LocalDate.of(2018, Month.SEPTEMBER, 05), "студент заочно"))));

        RESUME_3.getContacts().put(ContactsType.PHONE, "+3801235467");
        RESUME_3.getContacts().put(ContactsType.SKYPE, "gagarina6794");
        RESUME_3.getSections().put(SectionType.OBJECTIVE, new StringCategory("Junior Java Developer"));
        RESUME_3.getSections().put(SectionType.PERSONAL, new StringCategory("Аналитический склад ума"));
        RESUME_3.getSections().put(SectionType.ACHIEVEMENT, new StringListCategory());
        RESUME_3.getSections().put(SectionType.QUALIFICATIONS, new StringListCategory());
        RESUME_3.getSections().put(SectionType.EXPERIENCE, new OrganizationsMapCategory());
        RESUME_3.getSections().put(SectionType.EDUCATION, new OrganizationsMapCategory());
        ((ListCategory) RESUME_3.getSections().get(SectionType.ACHIEVEMENT)).save("Пока только бухгалтерские)");
        ((ListCategory) RESUME_3.getSections().get(SectionType.ACHIEVEMENT)).save("и спортивные)");
        ((ListCategory) RESUME_3.getSections().get(SectionType.QUALIFICATIONS)).save("Java junior");
        ((ListCategory) RESUME_3.getSections().get(SectionType.QUALIFICATIONS)).save("Bookkeeper");
        ((OrganizationsMapCategory) RESUME_3.getSections().get(SectionType.EXPERIENCE)).
                save(new Link("Работа 1", "http://javaops.ru/reg/basejava/1"),
                        new ArrayList<>(Arrays.asList(new Organizations(LocalDate.of(2006, Month.APRIL, 10),
                                LocalDate.of(2009, Month.SEPTEMBER, 05), "position1", "duties1"))));
        ((OrganizationsMapCategory) RESUME_3.getSections().get(SectionType.EXPERIENCE)).
                save(new Link("Работа 2", "http://javaops.ru/reg/basejava/2"),
                        new ArrayList<>(Arrays.asList(new Organizations(LocalDate.of(2007, Month.APRIL, 10),
                                LocalDate.of(2008, Month.SEPTEMBER, 05), "position1", "duties1"))));
        ((OrganizationsMapCategory) RESUME_3.getSections().get(SectionType.EDUCATION)).
                save(new Link("Учеба 1", "http://javaops.ru/1"),
                        new ArrayList<>(Arrays.asList(new Organizations(LocalDate.of(2003, Month.APRIL, 10),
                                LocalDate.of(2008, Month.SEPTEMBER, 05), "студент"))));
        ((OrganizationsMapCategory) RESUME_3.getSections().get(SectionType.EDUCATION)).
                save(new Link("Учеба 2", "http://javaops.ru/2"),
                        new ArrayList<>(Arrays.asList(new Organizations(LocalDate.of(2016, Month.APRIL, 10),
                                LocalDate.of(2018, Month.SEPTEMBER, 05), "студент заочно"))));
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