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

import com.project.finalproject.finalproject.model.PrintModel;
import com.project.finalproject.finalproject.service.PrintModelService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/printModels")
public class PrintModelRestController {

    @Autowired
    private PrintModelService printModelService;

    @GetMapping()
    public List<PrintModel> index() {
        List<PrintModel> printModels = printModelService.findAll();
        return printModels;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrintModel> show(@PathVariable("id") int id) {

        Optional<PrintModel> printModelAttempt = printModelService.findById(id);
        if (printModelAttempt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PrintModel>(printModelAttempt.get(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<PrintModel> filterByName(@RequestParam(name = "query") String query, Model model) {

        List<PrintModel> printModels = printModelService.findByName(query);

        return printModels;
    }

    // unused methods hidden for security
    // @PostMapping("/create")
    // public ResponseEntity<PrintModel> store(@RequestBody PrintModel printModel) {
    // return new ResponseEntity<PrintModel>(printModelService.create(printModel),
    // HttpStatus.OK);
    // }

    // @PostMapping("/{id}/edit")
    // public ResponseEntity<PrintModel> edit(@RequestBody PrintModel printModel,
    // @PathVariable("id") int id) {
    // if (printModelService.findById(id).isEmpty()) {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    // printModel.setId(id);
    // return new ResponseEntity<PrintModel>(printModelService.create(printModel),
    // HttpStatus.OK);
    // }

    // @PostMapping("/{id}/delete")
    // public ResponseEntity<PrintModel> delete(@PathVariable Integer id) {
    // if (printModelService.findById(id).isEmpty()) {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    // printModelService.deleteById(id);
    // return new ResponseEntity<PrintModel>(HttpStatus.OK);
    // }

}
