package com.dt170g.g3.backend.entities;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@NamedQueries({
//        @NamedQuery(
//                name = "EventRows.getEventPostCommentRows",
//                query = "SELECT e.title, e.description, e.startTime, p.imagePath, c.name, c.comment, c.dateAndTime " +
//                        "FROM Event e " +
//                        "LEFT JOIN Post p ON p.event = e " +
//                        "LEFT JOIN Comment c ON c.post = p " +
//                        "WHERE e.id = :eventId"
//        ),
        @NamedQuery(
                name = "Event.findAllWithPostsAndComments",
                query = "SELECT DISTINCT e FROM Event e " +
                        "LEFT JOIN FETCH e.posts " +
                        "LEFT JOIN FETCH e.comments " //+
//                        "ORDER BY e.startTime ASC"
        ),
        @NamedQuery(
                name = "Event.findAll",
                query = "SELECT e FROM Event e ORDER BY e.startTime ASC"
        )
//        @NamedQuery(
//                name = "Event.findAllWithPostsAndComments",
//                query = "SELECT DISTINCT e FROM Event e " +
//                        "LEFT JOIN FETCH e.comments"
//        )
})

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

    @OneToMany(mappedBy = "event")
    private List<Post> posts;


    @OneToMany(mappedBy = "event")
    private List<Comment> comments;



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

    public void setDescription(String description) {
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

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
