package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.strategy.PathStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    private Path directory;

    PathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
        setStrategy(new PathStrategy());
    }

    @Override
    protected Path getIndex(String uuid) {
        return new File(directory.toFile(), uuid).toPath();
    }

    @Override
    protected boolean checkIndex(Path path) {
        if (path.toFile().isFile()) {
            return Files.exists(path, LinkOption.NOFOLLOW_LINKS);
        }
        return false;
    }

    @Override
    protected Stream<Resume> getStream() {
        try {
            return Files.list(directory).map(this::doGet);
        } catch (IOException e) {
            System.out.println("Not such directory to get Stream" + e.getMessage());
        }
        return (new ArrayList<Resume>()).stream();
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        doUpdate(resume, path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return getStrategy().doRead(new BufferedInputStream(new FileInputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("FileStrategy read error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            getStrategy().doWrite(resume, new BufferedOutputStream(new FileOutputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("Write file error", resume.getUuid(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("FileStrategy delete error", path.getFileName().toString());
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("PathStrategy delete error", null);
        }
    }
}
