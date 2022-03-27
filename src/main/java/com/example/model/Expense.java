package com.example.model;


import java.util.Collection;
import java.util.Objects;

import javax.persistence.*;

@Entity
public
class Expense {

    private @Id @GeneratedValue Long id;

    private String title;
    private String description;

    @ManyToOne
    private Account owner;

    @ManyToOne
    private Group group;

    // private List of Accounts members
    @OneToMany
    private Collection<Entry> entires;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
        if (!(o instanceof Expense))
            return false;
        Expense account = (Expense) o;
        return Objects.equals(this.id, account.id) && Objects.equals(this.title, account.title)
                && Objects.equals(this.description, account.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.description);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", username='" + this.title + '\'' + ", email='" + this.description + '\'' + '}';
    }
}