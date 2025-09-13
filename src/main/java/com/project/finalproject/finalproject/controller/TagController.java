package com.project.finalproject.finalproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.finalproject.finalproject.model.Tag;
import com.project.finalproject.finalproject.service.TagService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping()
    public String index(Model model) {
        List<Tag> tags = tagService.findAll();
        model.addAttribute("tags", tags);
        return "tags/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        Optional<Tag> tagAttempt = tagService.findById(id);
        if (tagAttempt.isEmpty()) {
            return "notFound";
        }
        model.addAttribute("tag", tagAttempt.get());

        return "tags/show";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "query") String query, Model model) {

        List<Tag> tags = tagService.findByName(query);

        model.addAttribute("tags", tags);
        return "tags/index";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("tag", new Tag());
        return "tags/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("tag") Tag tagForm, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "tags/create";
        }
        Optional<Tag> existingTag = tagService.findByNameExact(tagForm.getName());
        if (existingTag.isPresent()) {
            bindingResult.rejectValue("name", "error", "A Tag with this name already exists.");
            return "Tags/create";
        }
        tagService.create(tagForm);
        return "redirect:/tags";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Tag> tagAttempt = tagService.findById(id);
        if (tagAttempt.isEmpty()) {
            return "notFound";
        }
        model.addAttribute("originalName", tagAttempt.get().getName()); // used to avoid edit page title updating with
                                                                        // duplicate name
        model.addAttribute("tag", tagAttempt.get());
        return "tags/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@Valid @ModelAttribute("tag") Tag tagForm, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "tags/edit";
        }

        Optional<Tag> tagAttempt = tagService.findById(tagForm.getId());
        if (tagAttempt.isEmpty()) {
            return "notFound";
        }

        Optional<Tag> existingTag = tagService.findByNameExact(tagForm.getName());
        if (existingTag.isPresent() && !existingTag.get().getId().equals(tagAttempt.get().getId())) {
            bindingResult.rejectValue("name", "error", "A Tag with this name already exists.");
            model.addAttribute("originalName", tagAttempt.get().getName());
            return "Tags/edit";
        }
        tagService.update(tagForm);
        return "redirect:/tags";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {

        Optional<Tag> tagAttempt = tagService.findById(id);
        if (tagAttempt.isEmpty()) {
            return "notFound";
        }
        tagService.delete(tagAttempt.get());
        return "redirect:/tags";
    }

}
