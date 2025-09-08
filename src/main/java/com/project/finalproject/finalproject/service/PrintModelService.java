package com.project.finalproject.finalproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.finalproject.finalproject.model.PrintModel;
import com.project.finalproject.finalproject.repository.PrintModelRepository;

@Service
public class PrintModelService {
    @Autowired
    private PrintModelRepository printModelRepository;

    public List<PrintModel> findAll() {
        return printModelRepository.findAll();
    }

    public Optional<PrintModel> findById(Integer id) {
        return printModelRepository.findById(id);
    }

    public PrintModel getById(Integer id) {
        return printModelRepository.findById(id).get();
    }

    public List<PrintModel> findByName(String query) {
        return printModelRepository.findByNameContainingIgnoreCaseOrTags_NameContainingIgnoreCase(query, query);
    }

    public PrintModel create(PrintModel printModel) {
        return printModelRepository.save(printModel);
    }

    public PrintModel update(PrintModel printModel) {

        return printModelRepository.save(printModel);
    }

    public void delete(PrintModel printModel) {
        printModelRepository.delete(printModel);
    }

    public void deleteById(Integer id) {
        printModelRepository.delete(findById(id).get());
    }

}
