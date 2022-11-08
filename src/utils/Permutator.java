package utils;

import java.lang.reflect.Array;
import java.util.*;

public class Permutator<T> implements Iterator<T[]> {

    private T[] array;

    private int[] indices;

    private boolean hasNext;

    private T[] outputArray;

    public Permutator(T[] originalArray)
    {
        array = originalArray.clone();

        indices = new int[originalArray.length];

        Map<T, Integer> hashMap = new HashMap<T, Integer>();
        for (int i = 0; i < originalArray.length; i++)
        {
            Integer item = hashMap.get(originalArray[i]);
            if (item == null)
            {
                hashMap.put(originalArray[i], i);
                item = i;
            }
            indices[i] = item.intValue();
        }
        Arrays.sort(indices);

        outputArray = (T[]) Array.newInstance(originalArray.getClass().getComponentType(), originalArray.length);

        fillOutputArray();

        hasNext = true;
    }

    private void fillOutputArray()
    {
        for (int i = 0; i < indices.length; i++)
            outputArray[i] = array[indices[i]];
    }

    public T[] getOutputArray()
    {
        return outputArray;
    }

    @Override
    public boolean hasNext()
    {
        return hasNext;
    }

    @Override
    public T[] next()
    {
        if (!hasNext)
            throw new NoSuchElementException();

        fillOutputArray();

        hasNext = getNextPermutation();

        return outputArray;
    }

    private boolean getNextPermutation()
    {
        boolean wasGenerated = false;

        int tail = indices.length - 1;
        while ((tail > 0) && !wasGenerated)
        {
            if (indices[tail - 1] < indices[tail])
            {
                int temp = indices.length - 1;

                while (indices[tail - 1] >= indices[temp]) temp--;

                swap(indices, tail - 1, temp);

                int i = tail;
                int j = indices.length - 1;
                while (i < j)
                {
                    swap(indices, i, j);
                    i++;
                    j--;
                }
                wasGenerated = true;
            }
            tail--;
        }

        return wasGenerated;
    }

    private void swap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public void remove() {

    }

    public void reinit()
    {
        Arrays.sort(indices);
        hasNext = true;
    }
}