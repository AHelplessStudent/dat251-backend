package com.example.controller;

import com.example.model.Account;
import com.example.model.Group;
import com.example.repository.AccountRepository;
import com.example.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private final GroupRepository repository;

    @Autowired
    private final AccountRepository accountRepository;

    GroupController(GroupRepository groupRepository, AccountRepository accountRepository) {
        this.repository = groupRepository;
        this.accountRepository = accountRepository;
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

    @PostMapping("/groups/{id}/members/{mid}")
    List<Account> addMember(@PathVariable Long id, @PathVariable Long mid) {
        Group group = repository.findById(id).get();
        Account acc = accountRepository.findById(mid).get();
        group.addMember(acc);

        accountRepository.save(acc);

        repository.save(group);

        return group.getMembers();
    }

    @PostMapping("/groups/{id}/members")
    List<Account> addMembers(@PathVariable Long id, @RequestBody List<Long> accIds) {

        Group group = repository.findById(id).get();

        for (Long accId : accIds) {
            Account acc = accountRepository.findById(accId).get();
            group.addMember(acc);
            accountRepository.save(acc);
        }


        repository.save(group);

        return group.getMembers();
    }
}
