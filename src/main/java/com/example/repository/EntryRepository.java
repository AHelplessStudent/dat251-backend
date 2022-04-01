package com.example.repository;

import com.example.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Item, Long> {
}