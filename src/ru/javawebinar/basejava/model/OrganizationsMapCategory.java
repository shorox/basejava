package ru.javawebinar.basejava.model;

import java.util.HashMap;
import java.util.List;

public class OrganizationsMapCategory extends MapCategory<Link, List<Organizations>> {

    public OrganizationsMapCategory() {
        super(new HashMap<>());
    }

    public void save(Link link, List<Organizations> list) {
        if (!category.containsKey(link)) {
            category.put(link, list);
        } else {
            List<Organizations> newList = category.get(link);
            newList.add(list.get(0));
            category.put(link, newList);
        }
    }
}
