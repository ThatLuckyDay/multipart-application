package com.pet.dao;

public interface SimpleAction<E> {
    void add(E elem);

    E update(E elem);

    void delete(int id);
}
