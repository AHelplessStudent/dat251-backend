package com.example.controller;

import com.example.model.Account;
import com.example.model.Group;
import com.example.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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

    // TODO properly delete
    @DeleteMapping("/groups/{id}")
    void deleteGroup(@PathVariable Long id) {
        Group group = repository.findById(id).get();
        List<Account> accs = group.getMembers();

        for (Account acc : accs) {
            group.removeMember(acc);
        }

        repository.deleteById(id);
    }

    @GetMapping("/groups/{id}/members")
    List<Account> members(@PathVariable Long id) { return repository.findById(id).get().getMembers(); }
}
