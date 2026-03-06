package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="image_path", length = 500)
    private String imagePath;

    @ManyToOne
    @JoinColumn(name="event_id", nullable=false)
    private Event event;


    public Post(){}

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}
