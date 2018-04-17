package ru.javawebinar.basejava.model;

import java.util.List;

public abstract class ListCategory<T> {

    protected List<T> list;

    public ListCategory(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void save(T data) {
        list.add(data);
    }

    public T read(int index) {
        return list.get(index);
    }

    public void update(T data, int index) {
        list.set(index, data);
    }

    public void delete(int index) {
        list.remove(index);
    }
}
