package com.example.model;


import java.util.Collection;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="groups")
public
class Group {

    private @Id @GeneratedValue Long id;
    private String name;
    private String email;
    // private Account owner? (is this really needed?)


    @ManyToMany
    private Collection<Account> members;

    @OneToMany
    private Collection<Expense> expenses;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Group))
            return false;
        Group account = (Group) o;
        return Objects.equals(this.id, account.id) && Objects.equals(this.name, account.name)
                && Objects.equals(this.email, account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.email);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", username='" + this.name + '\'' + ", email='" + this.email + '\'' + '}';
    }
}