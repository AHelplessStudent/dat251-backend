package com.example.controller;

import com.example.model.Account;
import com.example.model.Entry;
import com.example.model.Group;
import com.example.model.Item;
import com.example.repository.EntryRepository;
import com.example.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntryController {

    @Autowired
    private final EntryRepository repository;

    EntryController(EntryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/entries")
    List<Entry> all() {
        return repository.findAll();
    }

    @PostMapping("/entries")
    Entry newEntry(@RequestBody Entry newEntry) { return repository.save(newEntry); }

    @GetMapping("/entries/{id}")
    Entry one(@PathVariable Long id) { return repository.findById(id).get(); }

    // TODO properly delete
    @DeleteMapping("/entries/{id}")
    void deleteEntry(@PathVariable Long id) {
        Entry entry = repository.findById(id).get();
        repository.deleteById(id);
    }

    @GetMapping("/entries/{id}/items")
    List<Item> members(@PathVariable Long id) { return repository.findById(id).get().getItems(); }
}
