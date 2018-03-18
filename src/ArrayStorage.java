import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[2];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(String uuid, Resume resume) {
        Objects.requireNonNull(resume, "ERROR: resume can't be null for update!");
        String uuidNew = resume.getUuid();
        int indexOld = getIndex(uuid);
        int indexNew = getIndex(uuidNew);
        if (uuidNew == null || indexNew >= 0) {
            System.out.println("ERROR: Can't update this resume");
            return;
        }
        if (indexOld >= 0) {
            storage[indexOld] = resume;
        } else {
            System.out.println("ERROR: Resume was not found for update!");
        }
    }

    public void save(Resume resume) {
        Objects.requireNonNull(resume, "ERROR: resume can't be null for save!");
        String uuid = resume.getUuid();
        if (uuid == null) {
            return;
        }
        if (size >= storage.length) {
            System.out.println("ERROR: Free space is out for this save!");
            return;
        }
        if (getIndex(uuid) < 0) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("ERROR: Resume is already exist!");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("ERROR: Resume was not found!");
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            size--;
            storage[index] = storage[size];
            storage[size] = null;
            return;
        }
        System.out.println("ERROR: Resume was not found for delete!");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        if (size <= storage.length) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
