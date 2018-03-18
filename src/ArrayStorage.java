import java.util.Arrays;

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

    public void update(Resume r) {
        if (isPresent(r)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(r.uuid)) {
                    storage[i] = r;
                    return;
                }
            }
        }
        System.out.println("ERROR: Resume was not found for update!");
    }

    public void save(Resume r) {
        if (!isPresent(r)) {
            try {
                if(r.uuid!=null) {
                    storage[size] = r;
                    size++;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("ERROR: Free space is out for this save!");
            }
        } else System.out.println("ERROR: Resume is already exist!");
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Resume was not found!");
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                size--;
                storage[i] = storage[size];
                storage[size] = null;
                return;
            }
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

    private boolean isPresent(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                return true;
            }
        }
        return false;
    }
}
