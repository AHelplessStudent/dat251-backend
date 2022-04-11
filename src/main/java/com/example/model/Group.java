package com.example.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="groups")
public
class Group {

    private @Id @GeneratedValue Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("groups")
    private List<Account> members;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Entry> expenses;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addMember(Account member) {
        this.members.add(member);
        member.getGroups().add(this);
    }

    public void removeMember(Account member) {
        this.members.remove(member);
        member.getGroups().remove(this);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Group))
            return false;
        Group account = (Group) o;
        return Objects.equals(this.id, account.id) && Objects.equals(this.name, account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", username='" + this.name + '\''  +'}';
    }

    public List<Account> getMembers() {
        return members;
    }

    public void setMembers(List<Account> members) {
        this.members = members;
    }

    public List<Entry> getExpenses() {
        return expenses;
    }
}