package ru.javawebinar.basejava.storage.strategy;

public class SerializationContext {

    SerializationStrategy strategy;

    public SerializationStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(SerializationStrategy strategy) {
        this.strategy = strategy;
    }
}
