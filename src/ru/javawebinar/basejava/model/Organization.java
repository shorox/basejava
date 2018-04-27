package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class Organization {

    Link link;
    List<Position> listPositions;

    public Organization(Link link, List<Position> listPositions) {
        this.link = link;
        this.listPositions = listPositions;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "link=" + link +
                ", listPositions=" + listPositions +
                '}';
    }

    public Link getLink() {
        return link;
    }

    public List<Position> getList() {
        return listPositions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(link, that.link) &&
                Objects.equals(listPositions, that.listPositions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, listPositions);
    }
}
