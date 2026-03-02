package com.dt170g.g3.backend.beans;

import com.dt170g.g3.backend.entities.Booking;
import com.dt170g.g3.backend.services.BookingService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@Named
@ViewScoped
public class BookingBean implements Serializable {

    @Inject
    private BookingService bookingService;
    private String name;
    private String phone;
    private String email;
    private int guests = 2;
    private String notes;
    private LocalDate date;
    private String time;

    public void submitBooking() {

        // KOntroller för input i formuläret
        if (date == null) {
            addError("Välj ett datum.");
            return;
        }

        if (time == null || time.isBlank()) {
            addError("Välj en tid för att kunna boka bord.");
            return;
        }

        if (phone == null || !phone.matches("^[0-9+\\- ]{7,12}$")) {
            addError("Ange ett giltigt telefonnummer, riktnummer och 7-12 siffror.");
            return;
        }

        if (name == null || name.trim().length() < 2) {
            addError("Du måste ange ett namn (minst 2 tecken).");
            return;
        }

        //OWASP regex-key
        if (email == null || !email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            addError("Ange en giltig e-postadress.");
            return;
        }

        if (date.isBefore(LocalDate.now())) {
            addError("Du kan inte boka bord för en tid som är bakåt i tiden.");
            return;
        }



        // Parse tid
        LocalTime startTime;
        try {
            startTime = LocalTime.parse(time.trim());
        } catch (DateTimeParseException e) {
            addError("Ogiltig tid. Välj en tid i listan.");
            return;
        }

        // Om datum är idag, förhindra tid som redan passerat
        if (date.equals(LocalDate.now()) && startTime.isBefore(LocalTime.now())) {
            addError("Du kan inte boka en tid som redan passerat idag.");
            return;
        }

        if (guests <= 0) {
            addError("Antal gäster måste vara minst 1.");
            return;
        }

        // Bygger entity, sätter duration till 90 min som default
        Booking b = new Booking();
        b.setDate(date);
        b.setStartTime(startTime);
        b.setDurationMinutes(90);
        b.setNoOfPeople(guests);
        b.setNote(notes);
        b.setName(name == null ? "" : name.trim());
        b.setEmail(email == null ? null : email.trim().toLowerCase());
        b.setPhoneNo(phone == null ? null : phone.trim());

        // Försök skapa bokning
        try {
            boolean created = bookingService.createIfAvailable(b);
            if (!created) {
                //Om det inte finns lediga bord
                addError("Tyvärr, det finns inga lediga bord för " + guests + " personer den " + date + " kl " + time + ".");
                return;
            }
            
            msgSuccess(buildSuccessMessage(name, date, time));
            clearForm();

        } catch (Exception e) {
            // Finns det en dublett-bokning?
            addError("Du har redan bokat ett bord för " + guests + " personer i namnet " + name + " denna dag.");
        }
    }

    //rensas formuläret
    private void clearForm() {
        name = null;
        phone = null;
        email = null;
        notes = null;
        date = null;
        time = null;
        guests = 2;
    }

    // -----------------------
    // Messages
    // -----------------------

    private void addError(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Felmeddelande:", msg));
    }

    private void msgSuccess(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", msg));
    }

    private String buildSuccessMessage(String name, LocalDate date, String time) {
        String who = (name == null || name.isBlank()) ? "Din bokning" : ("Tack " + name + "!");
        return who + " Bokningsförfrågan mottagen för " + date + " kl " + time + ".";
    }

    // -----------------------
    // Getters & Setters
    // -----------------------

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getGuests() { return guests; }
    public void setGuests(int guests) { this.guests = guests; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
}