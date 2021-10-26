package com.example.csc2720;

import java.util.Arrays;

public class ArrayList<T extends Comparable<T>> implements ListInterface<T> {
   // Data
   private T[] list; // An array of objects with type T
   private int numberOfEntries;// Holds number of objects;
   private boolean integrityOK;
   private static final int DEFAULT_CAPACITY = 25;
   private static final int MAX_CAPACITY = 10000;

   // Constructor
   public ArrayList() {
      this(DEFAULT_CAPACITY);
   }

   public ArrayList(int initialCapacity) {
      integrityOK = false;
      // Is initialCapacity too small?
      if (initialCapacity < DEFAULT_CAPACITY) {
         initialCapacity = DEFAULT_CAPACITY;
      } else {
         checkCapacity(initialCapacity);
      }
      T[] tempList = (T[]) new Comparable[initialCapacity + 1];
      list = tempList;
      numberOfEntries = 0;
      integrityOK = true;
   }

   // Methods
   public void add(T newEntry) {
      checkIntegrity();
      list[numberOfEntries + 1] = newEntry;
      numberOfEntries++;
      ensureCapacity();
   }

   public void add(int newPosition, T newEntry) {
      checkIntegrity();
      if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
         if (newPosition <= numberOfEntries) {
            makeRoom(newPosition);
         }
         list[newPosition] = newEntry;
         numberOfEntries++;
         ensureCapacity();
      } else {
         throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
      }
   }

   public void makeRoom(int givenPosition) {
      int newIndex = givenPosition;
      int lastIndex = numberOfEntries;
      for (int index = lastIndex; index >= newIndex; index--) {
         list[index + 1] = list[index];
      }
   }

   public T remove(int givenPosition) {
      checkIntegrity();
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
         T result = list[givenPosition];
         if (givenPosition < numberOfEntries) {
            removeGap(givenPosition);
         }
         list[numberOfEntries] = null;
         numberOfEntries--;
         return result;
      } else {
         throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
      }
   }

   private void removeGap(int givenPosition) {
      int removeIndex = givenPosition;
      for (int index = removeIndex; index < numberOfEntries; index++) {
         list[index] = list[index + 1];
      }
   }

   public void clear() {
      checkIntegrity();
      for (int index = 0; index <= numberOfEntries; index++) {
         list[index] = null;
      }
      numberOfEntries = 0;
   }

   public T replace(int givenPosition, T newEntry) {
      checkIntegrity();
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
         T originalEntry = list[givenPosition]; // store the original value
         list[givenPosition] = newEntry;
         return originalEntry;
      } else {
         throw new IndexOutOfBoundsException("Out of Bounds.");
      }
   }

   public T getEntry(int givenPosition) {
      checkIntegrity();
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
         return list[givenPosition];
      } else {
         throw new IndexOutOfBoundsException("Out of Bounds.");
      }
   }

   public T[] toArray() {
      checkIntegrity();
      T[] result = (T[]) new Comparable[numberOfEntries];
      for (int index = 0; index < numberOfEntries; index++) {
         result[index] = list[index + 1];
      }
      return result;
   }

   public boolean contains(T anEntry) {
      checkIntegrity();
      boolean found = false;
      int index = 1;
      while (!found && (index <= numberOfEntries)) {
         if (anEntry.equals(list[index])) {
            found = true;
         }
         index++;
      }
      return found;
   }

   public int getLength() {
      return numberOfEntries;
   }

   public boolean isEmpty() {
      return numberOfEntries == 0;
   }

   private void ensureCapacity() {
      int capacity = list.length - 1;
      if (numberOfEntries >= capacity) {
         int newCapacity = 2 * capacity;
         checkCapacity(newCapacity);
         list = Arrays.copyOf(list, newCapacity + 1);
      }
   }

   private void checkIntegrity() {
      if (!integrityOK) {
         throw new SecurityException("A list is not integerated.");
      }
   }

   private void checkCapacity(int capacity) {
      if (capacity > MAX_CAPACITY) {
         throw new IllegalStateException("Reached the max capacity.");
      }
   }

   @Override
   public T getMin() {
      T[] sortedList = toArray();

      boolean isSorted = false;
      T tmp;
      while (!isSorted) {
         isSorted = true;
         for (int i = 0; i < sortedList.length - 1; i++) {
            if (sortedList[i].compareTo(sortedList[i + 1]) < 0) {
               tmp = sortedList[i];
               sortedList[i] = sortedList[i + 1];
               sortedList[i + 1] = tmp;
               isSorted = false;
            }
         }
      }
      return sortedList[numberOfEntries - 1];
   }

   public T removeMin() {
      T minValue = getMin();
      int minValueIdx = -1;
      T[] copyArray = toArray();

      for (int i = 0; i < copyArray.length - 1; i++) {
         if (copyArray[i].compareTo(minValue) == 0) {
            minValueIdx = i;
            break;
         }
      }

      remove(minValueIdx + 1);
      return minValue;
   }
}