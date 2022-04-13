package com.example.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class Account {

    private @Id @GeneratedValue Long id;

    private String username;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("members")
    private List<Group> groups;

    @OneToMany
    @JsonIgnoreProperties("owner")
    private List<Entry> entries;


    Account(String username, String email) {

        this.username = username;
        this.email = email;
    }


    public Account() {}

    public void addEntry(Entry entry) {
        this.entries.add(entry);
        entry.setOwner(this);
    }

    public void removeEntry(Entry entry) {
        this.entries.remove(entry);
        entry.setOwner(null);
    }

    public void addGroup(Group group) {
        this.groups.add(group);
        group.getMembers().add(this);
    }

    public void removeGroup(Group group) {
        this.groups.remove(group);
        group.getMembers().remove(this);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> expenses) {
        this.entries = expenses;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Account))
            return false;
        Account account = (Account) o;
        return Objects.equals(this.id, account.id) && Objects.equals(this.username, account.username)
                && Objects.equals(this.email, account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.username, this.email);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", username='" + this.username + '\'' + ", email='" + this.email + '\'' + '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}