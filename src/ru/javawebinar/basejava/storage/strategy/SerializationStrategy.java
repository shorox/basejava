package ru.javawebinar.basejava.storage.strategy;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public interface SerializationStrategy {

    void doWrite(Resume resume,Object fos) throws IOException;

    Resume doRead(Object fis) throws IOException;
}
