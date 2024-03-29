package com.example.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
public
class Entry {

    private @Id @GeneratedValue Long id;

    private String title;
    private String date;

    @ManyToOne
    @JsonIgnoreProperties({"entries","groups"})
    private Account owner;

    @ManyToOne
    @JsonIgnoreProperties({"entries","members"})
    private Group group;

    // private List of Accounts members
    @OneToMany
    private List<Item> items;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        if (!(o instanceof Entry))
            return false;
        Entry account = (Entry) o;
        return Objects.equals(this.id, account.id) && Objects.equals(this.title, account.title)
                && Objects.equals(this.date, account.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.date);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", username='" + this.title + '\'' + ", email='" + this.date + '\'' + '}';
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item newItem) {
        this.items.add(newItem);
        newItem.setEntry(this);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
        item.setEntry(null);
    }
}