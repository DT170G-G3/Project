package com.dt170g.g3.backend.beans;

import com.dt170g.g3.backend.entities.Comment;
import com.dt170g.g3.backend.entities.Event;
import com.dt170g.g3.backend.services.CommentService;
import com.dt170g.g3.backend.services.EventService;
import jakarta.annotation.PostConstruct;
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

    @Inject
    private CommentService commentService;

    private List<Event> events;

    private Event newEvent = new Event();





    @PostConstruct
    public void init() {
        events = eventService.findAll();
    }

    public void createEvent() {
        eventService.createEvent(newEvent);
        newEvent = new Event();
        events = eventService.findAll();
    }

    public void removeComment(int id){
        eventService.removeComment(id);
    }







    public List<Event> getEvents() {
        return events;
    }

    public List<Comment> commentsForEvent(int eventId) {
        return commentService.findByEventId(eventId);
    }

    public List<Comment> getAllComments() {
        return commentService.findAll();
    }

    public Event getNewEvent() {
        return newEvent;
    }

    public void setNewEvent(Event newEvent) {
        this.newEvent = newEvent;
    }




}