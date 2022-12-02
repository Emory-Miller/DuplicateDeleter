package com.zipcodewilmington.looplabs;

import java.lang.reflect.Array;


/**
 * Created by leon on 1/25/18.
 */
public abstract class DuplicateDeleter<T> implements DuplicateDeleterInterface<T> {
    protected T[] array;

    public DuplicateDeleter(T[] intArray) {
        this.array = intArray;
    }

    public T[] removeDuplicates(int maxNumberOfDuplications) {
        T[] arr = array.clone();
        for (T object : arr) {
            if (getNumberOfOccurrences(arr, object) >= maxNumberOfDuplications) {
                arr = removeValue((Class<T>) object.getClass(), arr, object);
            }
        }
        return arr;
    }

    public T[] removeDuplicatesExactly(int exactNumberOfDuplications) {
        T[] arr = array.clone();
        for (T object : arr) {
            if (getNumberOfOccurrences(arr, object) == exactNumberOfDuplications) {
                arr = removeValue((Class<T>) object.getClass(), arr, object);
            }
        }
        return arr;
    }

    public Integer getNumberOfOccurrences(T[] arr, T valueToEvaluate) {
        Integer count = 0;
        for (T t : arr) {
            if (t.equals(valueToEvaluate)) {
                count++;
            }
        }
        return count;
    }

    public T[] removeValue(Class<T> cls, T[] ar, T valueToRemove) {
        T[] arr = (T[]) Array.newInstance(cls, ar.length - getNumberOfOccurrences(ar, valueToRemove));

        int newArrIndex = 0;
        try {
            for (int i = 0; i < ar.length; i++) {
                if (ar[i] != valueToRemove) {
                    arr[newArrIndex] = ar[i];
                    newArrIndex++;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return arr;
        }
        return arr;
    }

}
