package com.att.oce.address.repository;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

import com.att.oce.address.*;

/**
 * This repository provides CRUD operations for {@link com.javaadvent.bootrest.todo.Todo}
 * objects.
 * @author pg939j
 */
public interface AddressRepository extends Repository<Address, String> {

    /**
     * Deletes a todo entry from the database.
     * @param deleted   The deleted todo entry.
     */
    void delete(Address deleted);

    /**
     * Finds all todo entries from the database.
     * @return  The information of all todo entries that are found from the database.
     */
    List<Address> findAll();

    /**
     * Finds the information of a single todo entry.
     * @param id    The id of the requested todo entry.
     * @return      The information of the found todo entry. If no todo entry
     *              is found, this method returns an empty {@link java.util.Optional} object.
     */
    Optional<Address> findOne(String id);

    /**
     * Saves a new todo entry to the database.
     * @param saved The information of the saved todo entry.
     * @return      The information of the saved todo entry.
     */
    Address save(Address saved);
}
