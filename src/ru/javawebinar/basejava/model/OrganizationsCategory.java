package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;

public class OrganizationsCategory extends Category<List<Organization>> {

    public OrganizationsCategory(List<Organization> category) {
        super(category);
    }

    public OrganizationsCategory(Organization... organizations) {
        this(Arrays.asList(organizations));
    }
}
