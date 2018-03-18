import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(String uuid, Resume resume) {
        Objects.requireNonNull(resume, "ERROR: resume can't be null for update!");
        if (check(uuid) >= 0) {
            if (check(resume.getUuid()) < 0) {
                storage[check(uuid)] = resume;
            } else System.out.println("ERROR: Such resume is already exist!");
        } else System.out.println("ERROR: Resume was not found for update!");
    }

    public void save(Resume resume) {
        Objects.requireNonNull(resume, "ERROR: resume can't be null for save!");
        if (check(resume.getUuid()) < 0) {
            if (resume.getUuid() != null) {
                if (size < storage.length) {
                    storage[size] = resume;
                    size++;
                } else System.out.println("ERROR: Free space is out for this save!");
            }
        } else System.out.println("ERROR: Resume is already exist!");
    }

    public Resume get(String uuid) {
        int i = check(uuid);
        if (i >= 0) {
            return storage[i];
        } else System.out.println("Resume was not found!");
        return null;
    }

    public void delete(String uuid) {
        int i = check(uuid);
        if (i >= 0) {
            size--;
            storage[i] = storage[size];
            storage[size] = null;
            return;
        } else System.out.println("ERROR: Resume was not found for delete!");
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

    private int check(String uuid) {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
