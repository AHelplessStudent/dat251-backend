package com.example.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.model.Account;
import com.example.model.Group;
import com.example.repository.AccountRepository;
import com.example.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AccountController {

    @Autowired
    private final AccountRepository repository;

    @Autowired
    private final GroupRepository groupRepository;

    AccountController(AccountRepository repository, GroupRepository groupRepository) {
        this.repository = repository;
        this.groupRepository = groupRepository;
    }


    @GetMapping("/accounts")
    List<Account> all() {
        return repository.findAll();
    }

    @PostMapping("/accounts")
    Account newAccount(@RequestBody Account newAccount) {
        return repository.save(newAccount);
    }

    // Single item

    @GetMapping("/accounts/{id}")
    Account one(@PathVariable Long id) {

        return repository.findById(id).get();
    }

    @PutMapping("/accounts/{id}")
    Account replaceAccount(@RequestBody Account newAccount, @PathVariable Long id) {

        return repository.findById(id)
                .map(account -> {
                    account.setUsername(newAccount.getUsername());
                    account.setEmail(newAccount.getEmail());
                    return repository.save(account);
                })
                .orElseGet(() -> {
                    newAccount.setId(id);
                    return repository.save(newAccount);
                });
    }

    @DeleteMapping("/accounts/{id}")
    void deleteAccount(@PathVariable Long id) {
        repository.deleteById(id);
    }


    // Create a group
    @PostMapping("/accounts/{id}/group")
    Group newGroup(@PathVariable Long id, @RequestBody Group newGroup) {

        Account member = repository.findById(id).get();

        member.addGroup(newGroup);

        groupRepository.save(newGroup);
        repository.save(member);

        return newGroup;
    }

}