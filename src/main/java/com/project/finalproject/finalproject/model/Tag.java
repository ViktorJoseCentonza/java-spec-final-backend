package com.project.finalproject.finalproject.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50, message = "name must not exceed the 50 character limit")
    @NotBlank(message = "must insert a name")
    private String name;

    @Size(max = 300, message = "description must not exceed the 300 character limit")
    private String description;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<PrintModel> printModels;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PrintModel> getPrintModels() {
        return this.printModels;
    }

    public void setPrintModels(List<PrintModel> models) {
        this.printModels = models;
    }

}
