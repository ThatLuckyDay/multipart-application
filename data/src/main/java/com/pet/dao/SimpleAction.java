package com.pet.dao;

public interface SimpleAction<E> {
    void add(E elem);

    void update(E elem);

    void delete(int id);
}
