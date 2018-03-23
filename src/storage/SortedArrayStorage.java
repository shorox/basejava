package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

    @Override
    protected int saveIndex(String uuid) {
        int saveIndex = Math.abs(getIndex(uuid)) - 1;
        int copyLength = size - saveIndex;
        int destinationIndex = saveIndex + 1;
        System.arraycopy(storage, saveIndex, storage, destinationIndex, copyLength);
        return saveIndex;
    }

    @Override
    protected void deleteByIndex(int index) {
        int indexPosition = index + 1;
        int copyLength = size - index;
        System.arraycopy(storage, indexPosition, storage, index, copyLength);
    }
}
