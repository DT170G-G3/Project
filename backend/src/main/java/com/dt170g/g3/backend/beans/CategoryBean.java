package com.dt170g.g3.backend.beans;

import com.dt170g.g3.backend.entities.Category;
import com.dt170g.g3.backend.services.CarteDishService;
import com.dt170g.g3.backend.services.CategoryService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("categoryBean")
public class CategoryBean implements Serializable {

    @Inject
    CategoryService categoryService;

    private List<Category> categories;

    @PostConstruct
    public void init(){
        this.categories = categoryService.findAllCategories();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
