package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name="table_order_carte_dish")
public class OrderCarteDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "table_order_id", nullable = false)
    private TableOrder order;

    @ManyToOne
    @JoinColumn(name = "carte_dish_id", nullable = false)
    private CarteDish carteDish;

    private int quantity;

    //Getter n' Setters
    public int getId(){
        return carteDish.getId();
    }
    public String getName(){
        return carteDish.getName();
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getQuantity(){
        return this.quantity;
    }


}
