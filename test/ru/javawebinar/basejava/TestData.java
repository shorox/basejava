package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.Month;
import java.util.UUID;

public class TestData {

    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();

    public static final Resume R1;
    public static final Resume R2;
    public static final Resume R3;
    public static final Resume R4;

    static {
        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");

        R1.addContact(ContactsType.MAIL, "mail111@ya.ru");
        R1.addContact(ContactsType.PHONE, "11111");
        R1.addCategory(SectionType.OBJECTIVE, new StringCategory("Objective1"));
        R1.addCategory(SectionType.PERSONAL, new StringCategory("Personal data"));
        R1.addCategory(SectionType.ACHIEVEMENT, new ListCategory("Achivment11", "Achivment12", "Achivment13"));
        R1.addCategory(SectionType.QUALIFICATIONS, new ListCategory("Java", "SQL", "JavaScript"));
        R1.addCategory(SectionType.EXPERIENCE,
                new OrganizationsCategory(
                        new Organization("Organization11", null,
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R1.addCategory(SectionType.EDUCATION,
                new OrganizationsCategory(
                        new Organization("Institute", "http://Organization11.ru",
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet"))));
        R2.addContact(ContactsType.MAIL, "mail2222@ya.ru");
        R2.addContact(ContactsType.PHONE, "22222");
        R2.addCategory(SectionType.OBJECTIVE, new StringCategory("Objective2"));
        R2.addCategory(SectionType.PERSONAL, new StringCategory("Personal data2"));
        R2.addCategory(SectionType.ACHIEVEMENT, new ListCategory("Achivment112", "Achivment122", "Achivment132"));
        R2.addCategory(SectionType.QUALIFICATIONS, new ListCategory("Java", "SQL", "JavaScript"));
        R2.addCategory(SectionType.EXPERIENCE,
                new OrganizationsCategory(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R2.addCategory(SectionType.EDUCATION,
                new OrganizationsCategory(
                        new Organization("Institute", "http://Organization121.ru",
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet"))));
        R3.addContact(ContactsType.MAIL, "mail333@ya.ru");
        R3.addContact(ContactsType.PHONE, "33333333");
        R3.addCategory(SectionType.OBJECTIVE, new StringCategory("Objective13"));
        R3.addCategory(SectionType.PERSONAL, new StringCategory("Personal data3"));
        R3.addCategory(SectionType.ACHIEVEMENT, new ListCategory("Achivment113", "Achivment123", "Achivment133"));
        R3.addCategory(SectionType.QUALIFICATIONS, new ListCategory("Java", "SQL", "JavaScript"));
        R3.addCategory(SectionType.EXPERIENCE,
                new OrganizationsCategory(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R3.addCategory(SectionType.EDUCATION,
                new OrganizationsCategory(
                        new Organization("Institute", "http://Organization11.ru",
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet"))));
        R4.addCategory(SectionType.OBJECTIVE, new StringCategory("Objective14"));
        R4.addCategory(SectionType.PERSONAL, new StringCategory("Personal data4"));
        R4.addCategory(SectionType.ACHIEVEMENT, new ListCategory("Achivment114", "Achivment124", "Achivment134"));
        R4.addCategory(SectionType.QUALIFICATIONS, new ListCategory("Java", "SQL", "JavaScript"));
    }
}
