package com.example.model;


import java.util.Collection;
import java.util.Objects;

import javax.persistence.*;

@Entity
public
class Entry {

    private @Id @GeneratedValue Long id;

    private String title;
    private String date;

    @ManyToOne
    private Account owner;

    @ManyToOne
    private Group group;

    // private List of Accounts members
    @OneToMany
    private Collection<Item> items;

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
}