package ru.javawebinar.basejava.model;

import java.util.Map;

public abstract class MapCategory<K, V> extends Category<Map<K, V>> {

    public MapCategory(Map<K,V> category) {
        super(category);
    }
}
