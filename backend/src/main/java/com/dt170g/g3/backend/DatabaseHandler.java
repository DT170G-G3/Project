package com.dt170g.g3.backend;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import com.dt170g.g3.backend.entities.Dish;
import java.util.List;

@ApplicationScoped
@Named("dbHandler")
public class DatabaseHandler {
    @PersistenceContext
    EntityManager entityManager;

    public List<Dish> getDishes(){
        TypedQuery<Dish> messageQuery = entityManager.createNamedQuery("Dish.getAll", Dish.class);
        List<Dish> resultList = messageQuery.getResultList();
        return resultList;
    }

    public String getDescriptionText(){
        List<Dish> messageList = getDishes();
        if(messageList.isEmpty()){
            return "NO MESSAGES!";
        }
        return messageList.get(0).getDescription();
    }
}

