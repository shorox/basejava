package ru.javawebinar.basejava.model;

public class MultiList {

    private String link;
    private String periodBegin;
    private String periodEnd;
    private String position;
    private String duties;

    public MultiList(String link, String periodBegin,String periodEnd, String position, String duties) {
        this.link = link;
        this.periodBegin = periodBegin;
        this.periodEnd = periodEnd;
        this.position = position;
        this.duties = duties;
    }

    public MultiList(String link,  String periodBegin,String periodEnd, String position) {
        this(link, periodBegin,periodEnd, position, null);
    }

    public String getLink() {
        return link;
    }

    public String getPeriodBegin() {
        return periodBegin;
    }

    public String getPeriodEnd() {
        return periodEnd;
    }

    public String getPosition() {
        return position;
    }

    public String getDuties() {
        return duties;
    }
}
