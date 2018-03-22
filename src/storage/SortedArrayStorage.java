package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void save(Resume resume) {
        String errorMessage = checkSaveConditions(resume);
        if (errorMessage != null) {
            System.out.println(errorMessage);
            return;
        }

        int saveIndex = Math.abs(getIndex(resume.getUuid())) - 1;
        int copyLength = size - saveIndex;
        int destinationIndex = saveIndex + 1;
        System.arraycopy(storage, saveIndex, storage, destinationIndex, copyLength);
        storage[saveIndex] = resume;
        size++;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            size--;
            int indexPosition = index + 1;
            int copyLength = size - index;
            System.arraycopy(storage, indexPosition, storage, index, copyLength);
            storage[size] = null;
            return;
        }
        System.out.println("Resume with " + uuid + " value was not found for delete!");
    }

    @Override
    protected int getIndex(String uuid) {
        if (uuid != null) {
            return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
        }
        return -1;
    }
}
