package com.project.finalproject.finalproject.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "printModels")
public class PrintModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50, message = "you've exceeded the 50 character limit")
    @NotBlank(message = "must insert a name")
    private String name;

    @Size(max = 350, message = "you've exceeded the 350 character limit")
    @NotBlank(message = "must insert a description")
    @Lob
    private String description;

    @Size(max = 2048, message = "you've exceeded the 2048 character limit")
    @NotBlank(message = "must insert an image url")
    @Lob
    private String image_url;

    @ManyToMany
    @JoinTable(name = "printModel_tag", joinColumns = @JoinColumn(name = "printModel_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
