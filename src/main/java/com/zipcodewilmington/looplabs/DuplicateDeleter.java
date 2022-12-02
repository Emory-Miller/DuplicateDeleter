package com.zipcodewilmington.looplabs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon on 1/25/18.
 */
public abstract class DuplicateDeleter<T> implements DuplicateDeleterInterface<T> {
    protected T[] array;

    public DuplicateDeleter(T[] intArray) {
        this.array = intArray;
    }

    public T[] removeDuplicates(int maxNumberOfDuplications){
        for (T object : this.array){
            if (getNumberOfOccurrences(object) >= maxNumberOfDuplications) {
                this.array = removeValue((Class<T>) object.getClass(), object);
            }
        }
        return this.array;
    }

    public T[] removeDuplicatesExactly(int exactNumberOfDuplications){
        for (T object : this.array){
            if (getNumberOfOccurrences(object) == exactNumberOfDuplications) {
                this.array = removeValue((Class<T>) object.getClass(), object);
            }
        }
        return this.array;
    }

    public Integer getNumberOfOccurrences(T valueToEvaluate) {
        Integer count = 0;
        for (T t : this.array){
            if(t.equals(valueToEvaluate)){
                count++;
            }
        }
        return count;
    }

    public T[] removeValue(Class<T> cls, T valueToRemove) {
        T[] arr = (T[]) Array.newInstance(cls, array.length - getNumberOfOccurrences(valueToRemove));

        int newArrIndex = 0;
        for (int i = 0; i < array.length; i++){
            if (array[i] != valueToRemove){
                arr[newArrIndex] = this.array[i];
                newArrIndex++;
            }
        }
        return arr;
    }

}
