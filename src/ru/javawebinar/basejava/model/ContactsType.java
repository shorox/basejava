package ru.javawebinar.basejava.model;

public enum ContactsType {

    ADDRESS("Адресс"),
    PHONE("Телефон"),
    SKYPE("Skype"),
    MAIL("Почта"),
    LINKEDIN("LinkedIn"),
    GITHUB("GitHub"),
    STACKOVERFLOW("Stackoverflow");

    private final String title;

    ContactsType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
