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
                                new Organization.Position(2005, Month.JANUARY, "position11", "content11"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2")),
                        new Organization("Organization12", "http://Organization12.ru",
                                new Organization.Position(2005, Month.JANUARY, "position12", "content12"))));

        R1.addCategory(SectionType.EDUCATION,
                new OrganizationsCategory(
                        new Organization("Institute1", "http://Organization1.ru",
                                new Organization.Position(1999, Month.JANUARY, 2003, Month.DECEMBER, "aspirant1", null),
                                new Organization.Position(2005, Month.MARCH, 2007, Month.JANUARY, "student1", "IT facultet1")),
                        new Organization("Institute2", "http://Organization2.ru",
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant2", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student2", "IT facultet2"))));
        R2.addContact(ContactsType.MAIL, "mail2222@ya.ru");
        R2.addContact(ContactsType.PHONE, "22222");
        R2.addCategory(SectionType.OBJECTIVE, new StringCategory("Objective2"));
        R2.addCategory(SectionType.PERSONAL, new StringCategory("Personal data2"));
        R2.addCategory(SectionType.ACHIEVEMENT, new ListCategory("Achivment112", "Achivment122", "Achivment132"));
        R2.addCategory(SectionType.QUALIFICATIONS, new ListCategory("Java", "SQL", "JavaScript"));
        R2.addCategory(SectionType.EXPERIENCE,
                new OrganizationsCategory(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R2.addCategory(SectionType.EDUCATION,
                new OrganizationsCategory(
                        new Organization("Institute2", "http://Organization2.ru",
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", null))));
        R3.addContact(ContactsType.MAIL, "mail333@ya.ru");
        R3.addContact(ContactsType.PHONE, "33333333");
        R3.addCategory(SectionType.OBJECTIVE, new StringCategory("Objective13"));
        R3.addCategory(SectionType.PERSONAL, new StringCategory("Personal data3"));
        R3.addCategory(SectionType.ACHIEVEMENT, new ListCategory("Achivment113", "Achivment123", "Achivment133"));
        R3.addCategory(SectionType.QUALIFICATIONS, new ListCategory("Java", "SQL", "JavaScript"));
        R3.addCategory(SectionType.EXPERIENCE,
                new OrganizationsCategory(
                        new Organization("Organization3", "http://Organization3.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R3.addCategory(SectionType.EDUCATION,
                new OrganizationsCategory(
                        new Organization("Institute3", "http://Organization3.ru",
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", null))));
        R4.addCategory(SectionType.OBJECTIVE, new StringCategory("Objective14"));
        R4.addCategory(SectionType.PERSONAL, new StringCategory("Personal data4"));
        R4.addCategory(SectionType.ACHIEVEMENT, new ListCategory("Achivment114", "Achivment124", "Achivment134"));
        R4.addCategory(SectionType.QUALIFICATIONS, new ListCategory("Java", "SQL", "JavaScript"));
    }
}
