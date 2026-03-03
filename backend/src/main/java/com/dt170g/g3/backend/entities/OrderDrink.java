package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name="table_order_drink")
public class OrderDrink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "table_order_id", nullable = false)
    private TableOrder order;

    @ManyToOne
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    private int quantity;

    //Getter n' Setters
    public int getId(){
        return drink.getId();
    }
    public String getName(){
        return drink.getName();
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getQuantity(){
        return this.quantity;
    }

}
