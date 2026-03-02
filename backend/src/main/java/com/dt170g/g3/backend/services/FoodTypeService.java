package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.entities.FoodType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
public class FoodTypeService {
    @PersistenceContext
    EntityManager entityManager;


    public List<FoodType> findAllFoodTypes(){
        TypedQuery<FoodType> messageQuery = entityManager.createNamedQuery("FoodType.getAll", FoodType.class);
        return messageQuery.getResultList();
    }

    public FoodType findById(int id){
        return entityManager.find(FoodType.class, id);
    }
}
