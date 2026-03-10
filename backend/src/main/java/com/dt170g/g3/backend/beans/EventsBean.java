package com.dt170g.g3.backend.beans;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Named("eventBean") // Explicitly naming it to match your XHTML
@ViewScoped
public class EventsBean implements Serializable {
    // ... existing map and lists ...
    private Map<LocalDate, List<String>> eventsByDate = new HashMap<>();
    private LocalDate selectedDate = LocalDate.now();
    private List<String> selectedEvents = new ArrayList<>();

    public EventsBean() {
        // Dummy test data
        eventsByDate.put(LocalDate.now(),
                List.of("Concert", "Board Meeting"));

        eventsByDate.put(LocalDate.now().plusDays(1),
                List.of("Birthday Party"));

        eventsByDate.put(LocalDate.now().plusDays(3),
                List.of("Conference"));
    }

    // Getters & setters

    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    public List<String> getSelectedEvents() {
        return selectedEvents;
    }

    public void onDateSelect(org.primefaces.event.SelectEvent<LocalDate> event) {
        this.selectedDate = event.getObject();
        this.selectedEvents = eventsByDate.getOrDefault(selectedDate, new ArrayList<>());

        org.primefaces.PrimeFaces.current().ajax().update("form:eventDialog");
        org.primefaces.PrimeFaces.current().executeScript("PF('eventDialogWidget').show()");
    }
}