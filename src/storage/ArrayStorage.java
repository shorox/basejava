package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    public void save(Resume resume) {
        String errorMessage = checkSaveConditions(resume);
        if (errorMessage != null) {
            System.out.println(errorMessage);
            return;
        }

        storage[size] = resume;
        size++;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            size--;
            storage[index] = storage[size];
            storage[size] = null;
            return;
        }
        System.out.println("Resume with : " + uuid + " was not found for delete!");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
