package com.project.finalproject.finalproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.finalproject.finalproject.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    public List<Tag> findByNameContainingIgnoreCase(String query);

    public Optional<Tag> findByNameIgnoreCase(String query);
}