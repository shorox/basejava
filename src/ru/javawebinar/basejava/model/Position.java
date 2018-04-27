package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Position {

    private final LocalDate periodBegin;
    private final LocalDate periodEnd;
    private final String position;
    private final String duties;

    public Position(LocalDate periodBegin, LocalDate periodEnd, String position, String duties) {
        Objects.requireNonNull(periodBegin, "periodBegin must not be null");
        Objects.requireNonNull(periodEnd, "periodEnd must not be null");
        Objects.requireNonNull(position, "position must not be null");

        this.periodBegin = periodBegin;
        this.periodEnd = periodEnd;
        this.position = position;
        this.duties = duties;
    }

    public Position(LocalDate periodBegin, LocalDate periodEnd, String position) {
        this(periodBegin, periodEnd, position, null);
    }

    @Override
    public String toString() {
        return "Position{" +
                ", periodBegin=" + periodBegin +
                ", periodEnd=" + periodEnd +
                ", position='" + position + '\'' +
                ", duties='" + duties + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(periodBegin, position.periodBegin) &&
                Objects.equals(periodEnd, position.periodEnd) &&
                Objects.equals(this.position, position.position) &&
                duties != null ? duties.equals(position.duties) : position.duties == null;
    }

    @Override
    public int hashCode() {
        int result =  periodBegin.hashCode();
        result = 31 * result + periodEnd.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + (duties != null ? duties.hashCode() : 0);
        return result;
    }

    public LocalDate getPeriodBegin() {
        return periodBegin;
    }

    public LocalDate getPeriodEnd() {
        return periodEnd;
    }

    public String getPosition() {
        return position;
    }

    public String getDuties() {
        return duties;
    }
}
