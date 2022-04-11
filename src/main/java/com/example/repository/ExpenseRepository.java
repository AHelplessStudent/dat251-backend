package com.example.repository;

import com.example.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Entry, Long> {
}