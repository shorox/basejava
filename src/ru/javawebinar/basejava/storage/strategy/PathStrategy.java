package ru.javawebinar.basejava.storage.strategy;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class PathStrategy implements SerializationStrategy {

    @Override
    public void doWrite(Resume resume, Object fos) throws IOException {
        try (ObjectOutputStream os = new ObjectOutputStream((FileOutputStream) fos)) {
            os.writeObject(resume);
        }
    }

    @Override
    public Resume doRead(Object fis) throws IOException {
        try (ObjectInputStream oi = new ObjectInputStream((FileInputStream) fis)) {
            return (Resume) oi.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}

