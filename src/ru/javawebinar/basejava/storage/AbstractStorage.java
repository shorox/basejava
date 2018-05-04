package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.strategy.SerializationContext;
import ru.javawebinar.basejava.storage.strategy.SerializationStrategy;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected SerializationContext context = new SerializationContext();

    protected abstract SK getIndex(String uuid);

    protected abstract boolean checkIndex(SK index);

    protected abstract Stream<Resume> getStream();

    protected abstract void doSave(Resume resume, SK index);

    protected abstract Resume doGet(SK index);

    protected abstract void doUpdate(Resume resume, SK index);

    protected abstract void doDelete(SK index);

    @Override
    public void save(Resume resume) {
        LOG.info("Save " + resume);
        Objects.requireNonNull(resume, "Bad news, we received null for save!");
        Objects.requireNonNull(resume.getUuid(), "Bad news, we can't save null input!");

        SK index = getNotExistedIndex(resume.getUuid());
        doSave(resume, index);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK index = getExistedIndex(uuid);
        return doGet(index);
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update " + resume);
        Objects.requireNonNull(resume, "Bad news, we received null for update!");

        SK index = getExistedIndex(resume.getUuid());
        doUpdate(resume, index);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK index = getExistedIndex(uuid);
        doDelete(index);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        return getStream().sorted().collect(Collectors.toList());
    }

    private SK getExistedIndex(String uuid) {
        SK index = getIndex(uuid);
        if (!checkIndex(index)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    private SK getNotExistedIndex(String uuid) {
        SK index = getIndex(uuid);
        if (checkIndex(index)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return index;
    }
}
