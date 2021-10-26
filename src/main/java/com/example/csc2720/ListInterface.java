package com.example.csc2720;

public interface ListInterface<T> {
  /*
   * Adds a new entry to the end of this list. Entries currently in the list are
   * unaffected. The list's size is increased by 1.
   * 
   * @param newEntry The object to be added as a new entry.
   */

  public void add(T newEntry);

  public void add(int newPosition, T newEntry);

  public T remove(int givenPosition);

  public void clear();

  public T replace(int givenPosition, T newEntry);

  public T getEntry(int givenPosition);

  public T[] toArray();

  public boolean contains(T anEntry);

  public int getLength();

  public boolean isEmpty();

  public T getMin();

  public T removeMin();
}