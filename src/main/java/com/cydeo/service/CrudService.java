package com.cydeo.service;

import java.util.List;

public interface CrudService <T,ID>{

    //in CrudService we include the methods that are common in ALL users
    T save(T user);
    T findById(ID username);
    List<T> findAll();
    void deleteById(ID id);
    void update(T object);
}
