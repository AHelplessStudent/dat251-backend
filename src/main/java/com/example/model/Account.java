package com.example.model;


import java.util.Collection;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class Account {

    private @Id @GeneratedValue Long id;

    private String username;

    private String email;
    @ManyToMany
    private Collection<Group> groups;

    @OneToMany
    private Collection<Expense> expenses;

    // private String hashed_password + salt or something
    //
    Account(String username, String email) {

        this.username = username;
        this.email = email;
    }


    public Account() {}


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

    public Collection<Group> getGroups() {
        return groups;
    }

    public void setGroups(Collection<Group> groups) {
        this.groups = groups;
    }

    public Collection<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(Collection<Expense> expenses) {
        this.expenses = expenses;
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
}