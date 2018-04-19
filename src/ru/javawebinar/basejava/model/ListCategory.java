package ru.javawebinar.basejava.model;

import java.util.List;

public abstract class ListCategory<T> extends Category<List> {

    public ListCategory(List<T> category) {
        super(category);
    }

    public void save(T data) {
        category.add(data);
    }

    public T read(int index) {
        return (T) category.get(index);
    }

    public void update(T data, int index) {
        category.set(index, data);
    }

    public void delete(int index) {
        category.remove(index);
    }
}
