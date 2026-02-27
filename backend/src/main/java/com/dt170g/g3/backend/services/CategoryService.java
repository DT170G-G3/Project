package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.entities.Category;
import com.dt170g.g3.backend.entities.LunchDish;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
@ApplicationScoped
public class CategoryService {
    @PersistenceContext
    EntityManager entityManager;

    public List<Category> findAllCategories(){
       TypedQuery<Category> messageQuery = entityManager.createNamedQuery("Category.getAll", Category.class);
        return messageQuery.getResultList();
    }

    public Category findById(Integer id){
        return entityManager.find(Category.class, id);
    }







}
