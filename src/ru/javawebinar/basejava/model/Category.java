package ru.javawebinar.basejava.model;

import java.util.Objects;

public abstract class Category<T> {

    protected final T category;

    public Category(T category) {
        Objects.requireNonNull(category, category.toString() + "must not be null");
        this.category = category;
    }

    public T getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category<?> category1 = (Category<?>) o;
        return Objects.equals(category, category1.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }
}
