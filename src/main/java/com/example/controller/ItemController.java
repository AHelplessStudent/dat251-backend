package com.example.controller;

import com.example.model.Item;
import com.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {


    @Autowired
    private final ItemRepository repository;

    ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/items")
    List<Item> all() {
        return repository.findAll();
    }

    @PostMapping("/items")
    Item newItem(@RequestBody Item newItem) { return repository.save(newItem); }

    @GetMapping("/items/{id}")
    Item one(@PathVariable Long id) { return repository.findById(id).get(); }

    // TODO properly delete
    @DeleteMapping("/items/{id}")
    void deleteItem(@PathVariable Long id) {
        Item Item = repository.findById(id).get();
        repository.deleteById(id);
    }

    // Create/add an item to an entry
    // velg ting i kvittering


}
