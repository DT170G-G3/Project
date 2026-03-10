package com.dt170g.g3.backend.beans;

import com.dt170g.g3.backend.entities.Comment;
import com.dt170g.g3.backend.entities.Event;
import com.dt170g.g3.backend.services.EventService;
import com.dt170g.g3.backend.utils.ProfanityFilter;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@ViewScoped
@Named("eventBean")
public class EventBean implements Serializable {

    @Inject
    private EventService eventService;

    private List<Event> events;

    // --- FÄLT FÖR ATT HANTERA XHTML-VYN ---
    private Event selectedEvent;
    private String newCommentName;
    private String newCommentText;

    @PostConstruct
    public void init() {
        // Körs automatiskt när sidan laddas
        events = eventService.findAllWithPostsAndComments();

        // Sätt det första eventet som förvalt
        if (events != null && !events.isEmpty()) {
            selectedEvent = events.get(0);
        }
    }

    //hämta kommande evenets
    public Event getNextEvent() {
        if (events == null) return null;

        LocalDateTime now = LocalDateTime.now();

        return events.stream()
                .filter(e -> e.getStartTime().isAfter(now)) // Hitta event i framtiden
                .min(Comparator.comparing(Event::getStartTime)) // Sortera så det närmaste väljs
                .orElse(null); // Om det inte finns några, returnera null
    }

    //hämta passerade events
    public List<Event> getPastEvents() {
        if (events == null) return null;

        LocalDateTime now = LocalDateTime.now();

        return events.stream()
                .filter(e -> e.getStartTime().isBefore(now)) // Filtrera fram de som har passerat
                .sorted(Comparator.comparing(Event::getStartTime).reversed()) // Sortera så det senaste ligger först i menyn
                .collect(Collectors.toList()); // Skapa en lista av dem
    }



    public List<Event> getEvents() {
        return events;
    }

    // ---  METODER FÖR SIDOMENYN ---
    public void selectEvent(Event event) {
        this.selectedEvent = event;
        // Rensa kommentarformuläret när man byter event
        this.newCommentName = "";
        this.newCommentText = "";
    }

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    // --- METODER FÖR ATT LÄGGA TILL KOMMENTAR ---
    public String getNewCommentName() { return newCommentName; }
    public void setNewCommentName(String newCommentName) { this.newCommentName = newCommentName; }

    public String getNewCommentText() { return newCommentText; }
    public void setNewCommentText(String newCommentText) { this.newCommentText = newCommentText; }

    public void addComment() {
        if (selectedEvent != null && newCommentText != null && !newCommentText.trim().isEmpty()) {
            Comment c = new Comment();
            String safeComment = ProfanityFilter.filterText(newCommentText);
            c.setName(newCommentName == null || newCommentName.trim().isEmpty() ? "Gäst" : newCommentName);
            c.setComment(safeComment);
            c.setDateAndTime(LocalDateTime.now());
            c.setEvent(selectedEvent);

            eventService.saveComment(c);

            // Lägger till den i listan lokalt så den syns på skärmen direkt
            selectedEvent.getComments().add(c);

            // Töm formuläret
            newCommentName = "";
            newCommentText = "";
        }
    }


}