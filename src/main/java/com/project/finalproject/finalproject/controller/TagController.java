package com.project.finalproject.finalproject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.finalproject.finalproject.model.Tag;
import com.project.finalproject.finalproject.service.TagService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

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

        model.addAttribute("tag", tagService.findById(id));
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
        tagService.create(tagForm);
        return "redirect:/tags";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("tag", tagService.findById(id));
        return "tags/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@Valid @ModelAttribute("tag") Tag tagForm, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "tags/edit";
        }
        tagService.update(tagForm);
        return "redirect:/tags";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {

        tagService.deleteById(id);
        return "redirect:/tags";
    }

}
