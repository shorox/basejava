package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.List;

public class MainResume {

    public static void main(String[] args) {

        Resume resume = new Resume("uuid1", "Ирина Грыцюк");

        resume.getContacts().put(ContactsType.PHONE, "+3801235467");
        resume.getContacts().put(ContactsType.SKYPE, "gagarina6794");

        resume.getSections().put(SectionType.OBJECTIVE, new StringCategory("Junior Java Developer"));
        resume.getSections().put(SectionType.PERSONAL, new StringCategory("Аналитический склад ума"));
        resume.getSections().put(SectionType.ACHIEVEMENT, new StringListCategory());
        resume.getSections().put(SectionType.QUALIFICATIONS, new StringListCategory());
        resume.getSections().put(SectionType.EXPERIENCE, new MultiListCategory());
        resume.getSections().put(SectionType.EDUCATION, new MultiListCategory());

        ((ListCategory) resume.getSections().get(SectionType.ACHIEVEMENT)).save("Пока только бухгалтерские)");
        ((ListCategory) resume.getSections().get(SectionType.ACHIEVEMENT)).save("и спортивные)");
        ((ListCategory) resume.getSections().get(SectionType.QUALIFICATIONS)).save("Java junior");
        ((ListCategory) resume.getSections().get(SectionType.QUALIFICATIONS)).save("Bookkeeper");
        ((ListCategory) resume.getSections().get(SectionType.EXPERIENCE)).
                save(new MultiList("link1", "time period1", "time period2", "position1", "duties1"));
        ((ListCategory) resume.getSections().get(SectionType.EXPERIENCE)).
                save(new MultiList("link2", "time period1", "time period2", "position2", "duties2"));
        ((ListCategory) resume.getSections().get(SectionType.EDUCATION)).
                save(new MultiList("link2", "time period1", "time period2", "course2"));
        ((ListCategory) resume.getSections().get(SectionType.EDUCATION)).
                save(new MultiList("link3", "time period1", "time period2", "course3"));

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
        List<MultiList> listExp = (List<MultiList>) resume.getSections().get(SectionType.EXPERIENCE).getCategory();
        listExp.forEach(x ->
                System.out.println("- " + x.getLink() + " " + x.getPeriodBegin() + " " + x.getPeriodEnd() + " " +
                        x.getPosition() + " " + x.getDuties()));
        System.out.println("________________________________");
        System.out.println(SectionType.EDUCATION.getTitle());
        List<MultiList> listEdu = (List<MultiList>) resume.getSections().get(SectionType.EDUCATION).getCategory();
        listEdu.forEach(x ->
                System.out.println("- " + x.getLink() + " " + x.getPeriodBegin() + " " + x.getPeriodEnd() + " " +
                        x.getPosition()));

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

        ((ListCategory) resume.getSections().get(SectionType.EXPERIENCE)).delete(1);
        ((ListCategory) resume.getSections().get(SectionType.EXPERIENCE)).update(new MultiList("newLink",
                "time periodB", "time periodE", "position2", "duties2"), 0);
        ((ListCategory) resume.getSections().get(SectionType.EXPERIENCE)).save(new MultiList("новый линк",
                "промежуток времени1", "промежуток времени2", "позиция", "обязаности"));
        MultiList educ = (MultiList) ((ListCategory) resume.getSections().get(SectionType.EXPERIENCE)).read(0);
        System.out.println(educ.getLink() + " " + educ.getPeriodBegin() + " " +
                educ.getPeriodEnd() + " " + educ.getPosition() + " " + educ.getDuties());//newLink

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
        List<MultiList> listExp2 = (List<MultiList>) resume.getSections().get(SectionType.EXPERIENCE).getCategory();
        listExp2.forEach(x ->
                System.out.println("- " + x.getLink() + " " + x.getPeriodBegin() + " " + x.getPeriodEnd() + " " +
                        x.getPosition() + " " + x.getDuties()));
        System.out.println("________________________________");
        System.out.println(SectionType.EDUCATION.getTitle());
        List<MultiList> listEdu2 = (List<MultiList>) resume.getSections().get(SectionType.EDUCATION).getCategory();
        listEdu2.forEach(x ->
                System.out.println("- " + x.getLink() + " " + x.getPeriodBegin() + " " + x.getPeriodEnd() + " " +
                        x.getPosition()));
    }
}
