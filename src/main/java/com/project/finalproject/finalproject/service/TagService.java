package com.project.finalproject.finalproject.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.finalproject.finalproject.model.Tag;
import com.project.finalproject.finalproject.repository.TagRepository;

@Service
public class TagService {

    @Autowired
    private TagRepository TagRepository;

    public List<Tag> findAll() {
        return TagRepository.findAll();
    }

    public Optional<Tag> findById(Integer id) {
        Optional<Tag> tagAttempt = TagRepository.findById(id);
        return tagAttempt;
    }

    public List<Tag> findByName(String query) {
        return TagRepository.findByNameContainingIgnoreCase(query);
    }

    public Tag create(Tag tag) {
        return TagRepository.save(tag);
    }

    public Tag update(Tag tag) {
        return TagRepository.save(tag);
    }

    public void delete(Tag tag) {
        TagRepository.delete(tag);
    }

    public void deleteById(Integer id) {
        TagRepository.deleteById(id);
    }
}
