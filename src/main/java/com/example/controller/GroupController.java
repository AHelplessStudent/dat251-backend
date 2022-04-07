package com.example.controller;

import com.example.model.Account;
import com.example.model.Group;
import com.example.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private final GroupRepository repository;


    GroupController(GroupRepository groupRepository) {
        this.repository = groupRepository;
    }

    @GetMapping("/groups")
    List<Group> all() {
        return repository.findAll();
    }

    @PostMapping("/groups")
    Group newGroup(@RequestBody Group newGroup) { return repository.save(newGroup); }

    @GetMapping("/groups/{id}")
    Group one(@PathVariable Long id) { return repository.findById(id).get(); }

    @DeleteMapping("/groups/{id}")
    void deleteGroup(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
