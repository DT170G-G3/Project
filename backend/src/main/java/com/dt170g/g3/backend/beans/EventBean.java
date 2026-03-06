package com.dt170g.g3.backend.beans;

import com.dt170g.g3.backend.entities.Event;
import com.dt170g.g3.backend.services.EventService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("eventBean")
public class EventBean implements Serializable {
    @Inject
    private EventService eventService;

    private List<Event> events;

    Event newEvent = new Event();


    public void createEvent(){
        eventService.createEvent(newEvent);
        newEvent = new Event();
    }


//    public void loadEvents(){
//        events = eventService.findAllWithPostsAndComments();
//    }

//    public List<Event> getEvents(){
//        return eventService.findAllWithPostsAndComments();
//    }

    public List<Event> getEvents(){
        return eventService.findAll();
    }


//    public List<Event> getEvents(){
//        return events;
//    }


    public Event getNewEvent() {
        return newEvent;
    }

    public void setNewEvent(Event newEvent) {
        this.newEvent = newEvent;
    }


}
