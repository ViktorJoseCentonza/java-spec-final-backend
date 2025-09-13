package com.project.finalproject.finalproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.finalproject.finalproject.model.PrintModel;

public interface PrintModelRepository extends JpaRepository<PrintModel, Integer> {
    public List<PrintModel> findByNameContainingIgnoreCaseOrTags_NameContainingIgnoreCase(String nameQuery,
            String tagQuery);

    public Optional<PrintModel> findByNameIgnoreCase(String nameQuery);
}
