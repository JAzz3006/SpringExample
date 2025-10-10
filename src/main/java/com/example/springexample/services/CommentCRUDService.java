package com.example.springexample.services;

import com.example.springexample.dto.CommentDto;

import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

public class CommentCRUDService implements CRUDService<CommentDto>{
    private final TreeMap<Integer, CommentDto> storage = new TreeMap<>();

    @Override
    public CommentDto getById(Integer id) {
        System.out.println("Get by ID " + id);
        if (!storage.containsKey(id)) {
            System.out.println("No such ID " + id);
            return null;
        }
        return storage.get(id);
    }

    @Override
    public Collection<CommentDto> getAll() {
        System.out.println("Get all");
        return storage.values();
    }

    @Override
    public void create(CommentDto item) {
        System.out.println("Create");
        Integer newKey = (storage.isEmpty() ? 0 : storage.lastKey()) + 1;
        item.setId(newKey);
        storage.put(newKey, item);
    }

    @Override
    public void update(Integer id, CommentDto item) {
        System.out.println("Update " + id);
        if (!storage.containsKey(id)) {
            System.out.println("No such id to update: " + id);
            return;
        }
        item.setId(id);
        storage.put(id, item);

    }

    @Override
    public void delete(Integer id) {
        System.out.println("Delete " + id);
        if (!storage.containsKey(id)){
            System.out.println("No such id to delete: " + id);
            return;
        }
        storage.remove(id);
    }
}
