package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;

public class ListCategory extends Category<List<String>> {

    public ListCategory(List<String> category) {
        super(category);
    }

    public ListCategory(String... items){
        super(Arrays.asList(items));
    }
}
