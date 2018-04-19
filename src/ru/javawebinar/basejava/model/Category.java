package ru.javawebinar.basejava.model;

public abstract class Category<T> {

    protected T category;

    public Category(T category) {
        this.category = category;
    }

    public T getCategory() {
        return category;
    }
}
