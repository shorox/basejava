package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainResume {

    public static void main(String[] args) {

        Resume resume = new Resume("uuid1", "Ирина Грыцюк");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LL/yyyy");

        resume.getContacts().put(ContactsType.PHONE, "+3801235467");
        resume.getContacts().put(ContactsType.SKYPE, "gagarina6794");

        resume.getSections().put(SectionType.OBJECTIVE, new StringCategory("Junior Java Developer"));
        resume.getSections().put(SectionType.PERSONAL, new StringCategory("Аналитический склад ума"));
        resume.getSections().put(SectionType.ACHIEVEMENT, new StringListCategory());
        resume.getSections().put(SectionType.QUALIFICATIONS, new StringListCategory());
        resume.getSections().put(SectionType.EXPERIENCE, new OrganizationsMapCategory());
        resume.getSections().put(SectionType.EDUCATION, new OrganizationsMapCategory());

        ((ListCategory) resume.getSections().get(SectionType.ACHIEVEMENT)).save("Пока только бухгалтерские)");
        ((ListCategory) resume.getSections().get(SectionType.ACHIEVEMENT)).save("и спортивные)");
        ((ListCategory) resume.getSections().get(SectionType.QUALIFICATIONS)).save("Java junior");
        ((ListCategory) resume.getSections().get(SectionType.QUALIFICATIONS)).save("Bookkeeper");

        ((OrganizationsMapCategory) resume.getSections().get(SectionType.EXPERIENCE)).
                save(new Link("Работа 1", "http://javaops.ru/reg/basejava/1"),
                        new ArrayList<>(Arrays.asList(new Organizations(LocalDate.of(2006, Month.APRIL, 10),
                                LocalDate.of(2009, Month.SEPTEMBER, 05), "position1", "duties1"))));

        ((OrganizationsMapCategory) resume.getSections().get(SectionType.EXPERIENCE)).
                save(new Link("Работа 2", "http://javaops.ru/reg/basejava/2"),
                        new ArrayList<>(Arrays.asList(new Organizations(LocalDate.of(2007, Month.APRIL, 10),
                                LocalDate.of(2008, Month.SEPTEMBER, 05), "position1", "duties1"))));

        ((OrganizationsMapCategory) resume.getSections().get(SectionType.EDUCATION)).
                save(new Link("Учеба 1", "http://javaops.ru/1"),
                        new ArrayList<>(Arrays.asList(new Organizations(LocalDate.of(2003, Month.APRIL, 10),
                                LocalDate.of(2008, Month.SEPTEMBER, 05), "студент"))));

        ((OrganizationsMapCategory) resume.getSections().get(SectionType.EDUCATION)).
                save(new Link("Учеба 2", "http://javaops.ru/2"),
                        new ArrayList<>(Arrays.asList(new Organizations(LocalDate.of(2016, Month.APRIL, 10),
                                LocalDate.of(2018, Month.SEPTEMBER, 05), "студент заочно"))));

        System.out.println(resume.getFullName());
        System.out.println("-------------------------");
        resume.getContacts().forEach((k, v) -> System.out.println("* " + k.getTitle() + " " + v));
        resume.getSections().forEach((k, v) -> {
            if (k.getTitle().equals(SectionType.PERSONAL.getTitle()) || k.getTitle().equals(SectionType.OBJECTIVE.getTitle())) {
                System.out.println("________________________________");
                System.out.println("* " + k.getTitle() + "\n" + v.getCategory());
            }
        });
        System.out.println("________________________________");
        System.out.println(SectionType.ACHIEVEMENT.getTitle());
        ((StringListCategory) resume.getSections().get(SectionType.ACHIEVEMENT))
                .getCategory().forEach(x -> System.out.println("- " + x));
        System.out.println("________________________________");
        System.out.println(SectionType.QUALIFICATIONS.getTitle());
        ((StringListCategory) resume.getSections().get(SectionType.QUALIFICATIONS))
                .getCategory().forEach(x -> System.out.println("- " + x));
        System.out.println("________________________________");
        System.out.println(SectionType.EXPERIENCE.getTitle());
        for (Map.Entry<Link, List<Organizations>> map : ((OrganizationsMapCategory) resume.getSections()
                .get(SectionType.EXPERIENCE)).getCategory().entrySet()) {
            System.out.println(map.getKey().getName() + "  " + map.getKey().getUrl());
            map.getValue().forEach(x -> System.out.println("- " + x.getPeriodBegin().format(formatter) + " " +
                    x.getPeriodEnd().format(formatter) + " " +
                    x.getPosition() + " " + x.getDuties()));
        }
        System.out.println("________________________________");
        System.out.println(SectionType.EDUCATION.getTitle());
        for (Map.Entry<Link, List<Organizations>> map : ((OrganizationsMapCategory) resume.getSections()
                .get(SectionType.EDUCATION)).getCategory().entrySet()) {
            System.out.println(map.getKey().getName() + "  " + map.getKey().getUrl());
            map.getValue().forEach(x -> System.out.println("- " + x.getPeriodBegin().format(formatter) + " " +
                    x.getPeriodEnd().format(formatter) + " " +
                    x.getPosition()));
        }

        System.out.println();
        System.out.println("Промежуточные результаты_________________________");
        resume.getContacts().remove(ContactsType.PHONE);
        resume.getContacts().put(ContactsType.SKYPE, "Ірина");
        resume.getContacts().put(ContactsType.ADDRESS, "Украина, г.Ровно");
        System.out.println(resume.getContacts().get(ContactsType.SKYPE));//Ірина
        resume.getSections().put(SectionType.PERSONAL, new StringCategory("Аналитический склад ума, исполнительность"));

        ((StringListCategory) resume.getSections().get(SectionType.ACHIEVEMENT)).delete(0);
        ((StringListCategory) resume.getSections().get(SectionType.ACHIEVEMENT)).update("DB MySQLite", 0);
        ((StringListCategory) resume.getSections().get(SectionType.ACHIEVEMENT)).save("SWING");
        System.out.println(((StringListCategory) resume.getSections().get(SectionType.ACHIEVEMENT)).read(1));//SWING

        ((OrganizationsMapCategory) resume.getSections().get(SectionType.EDUCATION)).
                save(new Link("Учеба 1", "http://javaops.ru/1"),
                        new ArrayList<>(Arrays.asList(new Organizations(LocalDate.of(2001, Month.APRIL, 10),
                                LocalDate.of(2006, Month.SEPTEMBER, 05), "студент new"))));
        ((OrganizationsMapCategory) resume.getSections().get(SectionType.EXPERIENCE)).getCategory()
                .remove(new Link("Работа 2", "http://javaops.ru/reg/basejava/2"));

        System.out.println("_____________________________________________________________________");
        System.out.println("После редактирования");
        System.out.println("_____________________________________________________________________");

        System.out.println(resume.getFullName());
        System.out.println("-------------------------");
        resume.getContacts().forEach((k, v) -> System.out.println("* " + k.getTitle() + " " + v));
        resume.getSections().forEach((k, v) -> {
            if (k.getTitle().equals(SectionType.PERSONAL.getTitle()) || k.getTitle().equals(SectionType.OBJECTIVE.getTitle())) {
                System.out.println("________________________________");
                System.out.println("* " + k.getTitle() + "\n" + v.getCategory());
            }
        });
        System.out.println("________________________________");
        System.out.println(SectionType.ACHIEVEMENT.getTitle());
        ((StringListCategory) resume.getSections().get(SectionType.ACHIEVEMENT))
                .getCategory().forEach(x -> System.out.println("- " + x));
        System.out.println("________________________________");
        System.out.println(SectionType.QUALIFICATIONS.getTitle());
        ((StringListCategory) resume.getSections().get(SectionType.QUALIFICATIONS))
                .getCategory().forEach(x -> System.out.println("- " + x));
        System.out.println("________________________________");
        System.out.println(SectionType.EXPERIENCE.getTitle());
        for (Map.Entry<Link, List<Organizations>> map : ((OrganizationsMapCategory) resume.getSections()
                .get(SectionType.EXPERIENCE)).getCategory().entrySet()) {
            System.out.println(map.getKey().getName() + "  " + map.getKey().getUrl());
            map.getValue().forEach(x -> System.out.println("- " + x.getPeriodBegin().format(formatter) + " " +
                    x.getPeriodEnd().format(formatter) + " " +
                    x.getPosition() + " " + x.getDuties()));
        }
        System.out.println("________________________________");
        System.out.println(SectionType.EDUCATION.getTitle());
        for (Map.Entry<Link, List<Organizations>> map : ((OrganizationsMapCategory) resume.getSections()
                .get(SectionType.EDUCATION)).getCategory().entrySet()) {
            System.out.println(map.getKey().getName() + "  " + map.getKey().getUrl());
            map.getValue().forEach(x -> System.out.println("- " + x.getPeriodBegin().format(formatter) + " " +
                    x.getPeriodEnd().format(formatter) + " " +
                    x.getPosition()));
        }
    }
}
