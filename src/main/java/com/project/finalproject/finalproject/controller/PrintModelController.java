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

import com.project.finalproject.finalproject.model.PrintModel;
import com.project.finalproject.finalproject.repository.TagRepository;
import com.project.finalproject.finalproject.service.PrintModelService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PrintModelController {

    @Autowired
    private PrintModelService printModelService;

    private final TagRepository tagRepository;

    PrintModelController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping()
    public String index(Model model) {
        List<PrintModel> printModels = printModelService.findAll();
        model.addAttribute("printModels", printModels);
        return "printModels/index";
    }

    @GetMapping("/printModels/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Optional<PrintModel> printModelAttempt = printModelService.findById(id);
        if (printModelAttempt.isEmpty()) {
            return "notFound";
        }
        model.addAttribute("printModel", printModelAttempt.get());
        return "printModels/show";
    }

    @GetMapping("/search")
    public String findByName(@RequestParam(name = "query") String query, Model model) {

        List<PrintModel> printModels = printModelService.findByName(query);

        model.addAttribute("printModels", printModels);
        return "printModels/index";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("printModel", new PrintModel());
        model.addAttribute("tags", tagRepository.findAll());
        return "printModels/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("printModel") PrintModel printModelForm, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tags", tagRepository.findAll());
            return "printModels/create";
        }

        Optional<PrintModel> existingPrintModel = printModelService.findByNameExact(printModelForm.getName());
        if (existingPrintModel.isPresent()) {
            bindingResult.rejectValue("name", "error", "A PrintModel with this name already exists.");
            model.addAttribute("tags", tagRepository.findAll());
            return "printModels/create";
        }
        printModelService.create(printModelForm);
        return "redirect:/";
    }

    @GetMapping("/printModels/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {

        Optional<PrintModel> printModelAttempt = printModelService.findById(id);
        if (printModelAttempt.isEmpty()) {
            return "notFound";
        }
        model.addAttribute("originalName", printModelAttempt.get().getName()); // used to avoid edit page title updating
                                                                               // with duplicate name
        model.addAttribute("printModel", printModelAttempt.get());

        model.addAttribute("tags", tagRepository.findAll());
        return "printModels/edit";
    }

    @PostMapping("/printModels/{id}/edit")
    public String update(@Valid @ModelAttribute("printModel") PrintModel printModelForm, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tags", tagRepository.findAll());
            return "printModels/edit";
        }

        Optional<PrintModel> printModelAttempt = printModelService.findById(printModelForm.getId());
        if (printModelAttempt.isEmpty()) {
            return "notFound";
        }

        Optional<PrintModel> existingPrintModel = printModelService.findByNameExact(printModelForm.getName());
        if (existingPrintModel.isPresent()
                && !existingPrintModel.get().getId().equals(printModelAttempt.get().getId())) {
            bindingResult.rejectValue("name", "error", "A PrintModel with this name already exists.");
            model.addAttribute("originalName", printModelAttempt.get().getName());
            return "printModels/edit";
        }

        printModelService.update(printModelForm);
        return "redirect:/";
    }

    @PostMapping("/printModels/{id}/delete")
    public String delete(@PathVariable Integer id, Model model) {

        Optional<PrintModel> printModelAttempt = printModelService.findById(id);
        if (printModelAttempt.isEmpty()) {
            return "notFound";
        }

        printModelService.delete(printModelAttempt.get());

        return "redirect:/";
    }

}
