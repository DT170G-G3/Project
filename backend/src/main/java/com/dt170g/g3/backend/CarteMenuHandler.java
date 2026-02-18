package com.dt170g.g3.backend;

import com.dt170g.g3.backend.entities.CarteMenu;
import com.dt170g.g3.backend.entities.Dish;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.inject.Named;


import java.util.List;
@ApplicationScoped
@Named("carteMenuHandler")
public class CarteMenuHandler {
    @PersistenceContext
    EntityManager entityManager;



    public List<Dish> getCarteMenu(){

        CarteMenu menu = entityManager.find(CarteMenu.class, 1L);

        if(menu != null) {
            return menu.getDishes();
        }else {
            return List.of();
        }

    }





}
