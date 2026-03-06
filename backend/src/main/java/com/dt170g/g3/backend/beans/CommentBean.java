package com.dt170g.g3.backend.beans;

import com.dt170g.g3.backend.entities.Comment;
import com.dt170g.g3.backend.entities.Event;
import com.dt170g.g3.backend.services.CommentService;
import com.dt170g.g3.backend.services.EventService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.time.LocalDateTime;
import java.util.List;
@RequestScoped
@Named("commentBean")
public class CommentBean {

    @Inject
    private CommentService commentService;

    @Inject
    private EventService eventService;

    private Comment newComment = new Comment();

    public String createComment(Integer eventId){
        Event event = eventService.getEventById(eventId);

        if (event == null) {
            System.out.println("Event hittades inte för id: " + eventId);
            return null;
        }

        newComment.setEvent(event);
        newComment.setDateAndTime(LocalDateTime.now());
        commentService.createComment(newComment);

        newComment = new Comment();

        return "testEvent?faces-redirect=true";
    }

    public void setNewComment(Comment newComment) {
        this.newComment = newComment;
    }

    public Comment getNewComment() {
        return newComment;
    }
}