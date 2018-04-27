package ru.javawebinar.basejava.model;

import java.util.List;

public class OrganizationsCategory extends Category <List<Organization>> {

    public OrganizationsCategory(List<Organization> category) {
        super(category);
    }
}
