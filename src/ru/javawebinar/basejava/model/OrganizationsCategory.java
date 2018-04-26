package ru.javawebinar.basejava.model;

import java.util.List;

public class OrganizationsCategory extends Category <List<Organizations>> {

    public OrganizationsCategory(List<Organizations> category) {
        super(category);
    }
}
