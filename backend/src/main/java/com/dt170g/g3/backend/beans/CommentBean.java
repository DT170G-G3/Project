package com.dt170g.g3.backend.beans;

import com.dt170g.g3.backend.entities.Comment;
import com.dt170g.g3.backend.entities.Event;
import com.dt170g.g3.backend.services.CommentService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named("commentBean")
@ViewScoped
public class CommentBean implements Serializable {

    @Inject
    private CommentService commentService;

    private Event selectedEvent;

    private String name;
    private String commentText;

    public void createComment() {

        Comment comment = new Comment();
        comment.setEvent(selectedEvent);
        comment.setName(name);
        comment.setComment(commentText);
        comment.setDateAndTime(LocalDateTime.now());

        commentService.createComment(comment);

        name = null;
        commentText = null;
    }

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public List<Comment> getAllComments() {
        return commentService.findAll();
    }
}