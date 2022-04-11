package com.example.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public
class Item {

    private @Id @GeneratedValue Long id;

    private String title;
    private double cost;
    private boolean counted;

    @ManyToOne
    @JsonIgnoreProperties("items")
    private Entry entry;

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
        if (!(o instanceof Item))
            return false;
        Item account = (Item) o;
        return Objects.equals(this.id, account.id) && Objects.equals(this.title, account.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.cost);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", username='" + this.title + '\'' + ", email='" + this.cost + '\'' + '}';
    }

    public boolean isCounted() {
        return counted;
    }

    public void setCounted(boolean counted) {
        this.counted = counted;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
}