package com.project.finalproject.finalproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.finalproject.finalproject.model.Tag;
import com.project.finalproject.finalproject.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/tags")
public class TagRestController {

    @Autowired
    private TagService tagService;

    @GetMapping()
    public List<Tag> index() {
        List<Tag> tags = tagService.findAll();
        return tags;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> show(@PathVariable("id") Integer id) {
        Optional<Tag> tagAttempt = tagService.findById(id);
        if (tagAttempt.isEmpty()) {
            return new ResponseEntity<Tag>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Tag>(tagAttempt.get(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<Tag> search(@RequestParam(name = "query") String query, Model model) {

        List<Tag> tags = tagService.findByName(query);
        return tags;
    }
    // unused methods hidden for security
    // @PostMapping("/create")
    // public ResponseEntity<Tag> store(@RequestBody Tag tag) {
    // return new ResponseEntity<Tag>(tagService.create(tag), HttpStatus.OK);
    // }

    // @PostMapping("/{id}/edit")
    // public ResponseEntity<Tag> update(@RequestBody Tag tag, @PathVariable("id")
    // int id) {
    // if (tagService.findById(id).isEmpty()) {
    // return new ResponseEntity<Tag>(HttpStatus.NOT_FOUND);
    // }
    // return new ResponseEntity<Tag>(tagService.create(tag), HttpStatus.OK);
    // }

    // @PostMapping("/{id}/delete")
    // public ResponseEntity<Tag> delete(@PathVariable Integer id) {
    // if (tagService.findById(id).isEmpty()) {
    // return new ResponseEntity<Tag>(HttpStatus.NOT_FOUND);
    // }
    // tagService.deleteById(id);
    // return new ResponseEntity<Tag>(HttpStatus.OK);
    // }

}
