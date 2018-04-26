package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class Organizations {

    Link link;
    List<Positions> list;

    public Organizations(Link link, List<Positions> list) {
        this.link = link;
        this.list = list;
    }

    @Override
    public String toString() {
        return "Organizations{" +
                "link=" + link +
                ", list=" + list +
                '}';
    }

    public Link getLink() {
        return link;
    }

    public List<Positions> getList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organizations that = (Organizations) o;
        return Objects.equals(link, that.link) &&
                Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, list);
    }
}
