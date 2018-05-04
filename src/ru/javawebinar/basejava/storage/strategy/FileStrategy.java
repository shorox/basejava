package ru.javawebinar.basejava.storage.strategy;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class FileStrategy implements SerializationStrategy {

    @Override
    public void doWrite(Resume r, Object os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream((OutputStream) os)) {
            oos.writeObject(r);
        }
    }

    @Override
    public Resume doRead(Object is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream((InputStream) is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}