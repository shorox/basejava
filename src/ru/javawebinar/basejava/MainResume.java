package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.List;

public class MainResume {

    public static void main(String[] args) {

        Resume resume = new Resume("uuid1", "Ирина Грыцюк");

        resume.setContacts(new ContactsMap());
        resume.setObjective(new StringCategory("Junior Java Developer"));
        resume.setPersonal(new StringCategory("Аналитический склад ума"));
        resume.setAchievement(new StringListCategory());
        resume.setQualifications(new StringListCategory());
        resume.setExperience(new EducationListCategory());
        resume.setEducation(new EducationListCategory());

        resume.getContacts().create("skype", "gagarina6794");
        resume.getContacts().create("телефон", "+3801235478");
        resume.getAchievement().save("Пока только бухгалтерские)");
        resume.getAchievement().save("и спортивные)");
        resume.getQualifications().save("Java junior");
        resume.getQualifications().save("Bookkeeper");
        resume.getExperience().save(new Education("link1", "time period1", "position1", "duties1"));
        resume.getExperience().save(new Education("link2", "time period2", "position2", "duties2"));
        resume.getEducation().save(new Education("link2", "time period2", "course2"));
        resume.getEducation().save(new Education("link3", "time period3", "course3"));

        System.out.println(resume.getFullName());
        System.out.println("-------------------------");
        resume.getContacts().getContacts().forEach((k, v) -> System.out.println("* " + k + " " + v));
        System.out.println("-------------------------");
        System.out.println(SectionType.OBJECTIVE.getTitle());
        System.out.println("- " + resume.getObjective().getCategory());
        System.out.println("-------------------------");
        System.out.println(SectionType.PERSONAL.getTitle());
        System.out.println("- " + resume.getPersonal().getCategory());
        System.out.println("-------------------------");
        System.out.println(SectionType.ACHIEVEMENT.getTitle());
        resume.getAchievement().getList().forEach(x -> System.out.println("- " + x));
        System.out.println("-------------------------");
        System.out.println(SectionType.QUALIFICATIONS.getTitle());
        resume.getQualifications().getList().forEach(x -> System.out.println("- " + x));
        System.out.println("-------------------------");
        System.out.println(SectionType.EXPERIENCE.getTitle());
        List<Education> listExp = resume.getExperience().getList();
        listExp.forEach(x ->
                System.out.println("- " + x.getLink() + " " + x.getPeriod() + " " + x.getPosition() + " " + x.getDuties()));
        System.out.println("-------------------------");
        System.out.println(SectionType.EDUCATION.getTitle());
        List<Education> listEdu = resume.getEducation().getList();
        listEdu.forEach(x ->
                System.out.println("- " + x.getLink() + " " + x.getPeriod() + " " + x.getPosition()));

        System.out.println();
        System.out.println("Промежуточные результаты_________________________");
        resume.getContacts().delete("телефон");
        resume.getContacts().update("skype", "Ірина");
        resume.getContacts().create("адрес", "Украина, г.Ровно");
        System.out.println(resume.getContacts().read("skype"));//Ірина

        System.out.println(resume.getPersonal().getCategory());//Аналитический склад ума
        resume.setPersonal(new StringCategory("Аналитический склад ума, исполнительность"));

        resume.getAchievement().delete(0);
        resume.getAchievement().update("DB MySQLite", 0);
        resume.getAchievement().save("SWING");
        System.out.println(resume.getAchievement().read(1));//SWING

        resume.getExperience().delete(1);
        resume.getExperience().update(new Education("newLink", "time period2", "position2", "duties2"), 0);
        resume.getExperience().save(new Education("новый линк", "промежуток времени", "позиция", "обязаности"));
        Education educ = (Education) resume.getExperience().read(0);
        System.out.println(educ.getLink() + " " + educ.getPeriod() + " " + educ.getPosition() + " " + educ.getDuties());//newLink

        System.out.println("_____________________________________________________________________");
        System.out.println("После редактирования");
        System.out.println("_____________________________________________________________________");

        System.out.println(resume.getFullName());
        System.out.println("-------------------------");
        resume.getContacts().getContacts().forEach((k, v) -> System.out.println("* " + k + " " + v));
        System.out.println("-------------------------");
        System.out.println(SectionType.OBJECTIVE.getTitle());
        System.out.println("- " + resume.getObjective().getCategory());
        System.out.println("-------------------------");
        System.out.println(SectionType.PERSONAL.getTitle());
        System.out.println("- " + resume.getPersonal().getCategory());
        System.out.println("-------------------------");
        System.out.println(SectionType.ACHIEVEMENT.getTitle());
        resume.getAchievement().getList().forEach(x -> System.out.println("- " + x));
        System.out.println("-------------------------");
        System.out.println(SectionType.QUALIFICATIONS.getTitle());
        resume.getQualifications().getList().forEach(x -> System.out.println("- " + x));
        System.out.println("-------------------------");
        System.out.println(SectionType.EXPERIENCE.getTitle());
        List<Education> listExp1 = resume.getExperience().getList();
        listExp1.forEach(x ->
                System.out.println("- " + x.getLink() + " " + x.getPeriod() + " " + x.getPosition() + " " + x.getDuties()));
        System.out.println("-------------------------");
        System.out.println(SectionType.EDUCATION.getTitle());
        List<Education> listEdu1 = resume.getEducation().getList();
        listEdu1.forEach(x ->
                System.out.println("- " + x.getLink() + " " + x.getPeriod() + " " + x.getPosition()));
    }
}
