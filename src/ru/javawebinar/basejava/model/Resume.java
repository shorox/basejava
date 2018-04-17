package ru.javawebinar.basejava.model;

import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume> {

    private final String uuid;
    private String fullName;
    private ContactsMap contacts;
    private StringCategory personal;
    private StringCategory objective;
    private ListCategory achievement;
    private ListCategory qualifications;
    private ListCategory experience;
    private ListCategory education;

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public ContactsMap getContacts() {
        return contacts;
    }

    public void setContacts(ContactsMap contacts) {
        this.contacts = contacts;
    }

    public StringCategory getPersonal() {
        return personal;
    }

    public void setPersonal(StringCategory personal) {
        this.personal = personal;
    }

    public StringCategory getObjective() {
        return objective;
    }

    public void setObjective(StringCategory objective) {
        this.objective = objective;
    }

    public ListCategory getAchievement() {
        return achievement;
    }

    public void setAchievement(StringListCategory achievement) {
        this.achievement = achievement;
    }

    public ListCategory getQualifications() {
        return qualifications;
    }

    public void setQualifications(StringListCategory qualifications) {
        this.qualifications = qualifications;
    }

    public ListCategory getExperience() {
        return experience;
    }

    public void setExperience(EducationListCategory experience) {
        this.experience = experience;
    }

    public ListCategory getEducation() {
        return education;
    }

    public void setEducation(EducationListCategory education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return fullName + " : " + uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) &&
                fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }
}
