/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {

      storage = new Resume[10000];
      size = 0;

    }

    void save(Resume r) {

        storage[size] = r;
        size++;

    }

    Resume get(String uuid) {

        for (int i = 0; i < size; i++) {

            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }

        return null;
    }

    void delete(String uuid) {

        for (int i = 0; i < size; i++) {

            if (storage[i].toString().equals(uuid)) {
                size--;
                storage[i] = storage[size];
                storage[size] = null;
            }
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {

        Resume[] storageNew = new Resume[size];

        System.arraycopy(storage, 0, storageNew, 0, size);

        return storageNew;
    }

    int size() {
        return size;
    }
}
