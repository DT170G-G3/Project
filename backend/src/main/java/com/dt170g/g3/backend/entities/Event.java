package com.dt170g.g3.backend.entities;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="event")
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="title", length=255, nullable=false)
    private String title;

    @Column(name="description", nullable=false)
    private String description;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    public Event(){}

    //setters and getters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String text) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
