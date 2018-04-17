package ru.javawebinar.basejava.model;

public class Education {

    private String link;
    private String period;
    private String position;
    private String duties;

    public Education(String link, String period, String position, String duties) {
        this.link = link;
        this.period = period;
        this.position = position;
        this.duties = duties;
    }

    public Education(String link, String period, String position) {
        this(link, period, position, null);
    }

    public String getLink() {
        return link;
    }

    public String getPeriod() {
        return period;
    }

    public String getPosition() {
        return position;
    }

    public String getDuties() {
        return duties;
    }
}
