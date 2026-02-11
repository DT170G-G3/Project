package com.dt170g.g3.backend;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import com.dt170g.g3.backend.entities.Dish;
import java.util.List;

@ApplicationScoped
@Named("dish")
public class DishHandler {
    @PersistenceContext
    EntityManager entityManager;

    public Dish getDishById(int id){
        Dish dish = entityManager.find(Dish.class, id);
        return dish;
    }
    public List<Dish> getDishes(){
        TypedQuery<Dish> messageQuery = entityManager.createNamedQuery("Dish.getAll", Dish.class);
        List<Dish> resultList = messageQuery.getResultList();
        return resultList;
    }

    public String getDishName(){
        Dish dish = getDishById(1);
        if(dish.getName().isEmpty()){
            return "NO MESSAGES!";
        }
        return dish.getName();
    }

}

