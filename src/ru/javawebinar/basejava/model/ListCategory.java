package ru.javawebinar.basejava.model;

import java.util.List;

public class ListCategory extends Category<List<String>> {

    public ListCategory(List<String> category) {
        super(category);
    }

}
