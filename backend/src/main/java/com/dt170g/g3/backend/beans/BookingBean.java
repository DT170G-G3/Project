package com.dt170g.g3.backend.beans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;

@Named
@ViewScoped
public class BookingBean implements Serializable {

    private String name;
    private String phone;
    private String email;
    private int guests = 2;
    private String notes;
    
    private LocalDate date;
    private String time;



    public void submitBooking() {

        //har vi valt tid?
        if (time == null || time.isBlank()) {
            addError("Välj en tid för att kunna boka bord.");
            return;
        }

        // Är datum i dåtid?
        if (date != null && date.isBefore(LocalDate.now())) {
            addError("Du kan inte boka bord för en tid som är bakåt i tiden.");
            return;
        }


        // Kolla om det finns lediga bord (Mock-logik just nu)
        // TODO: Anropa BookingService.checkAvailability(...)
        if (!isTableAvailable(date, time, guests)) {
            addError("Tyvärr, det finns inga lediga bord för " + guests + " personer den " + date + " kl " + time);
            return;
        }


        // Allt ok -> "Spara"
        // TODO: Anropa BookingService.createBooking(...)

        //bygger ihop ett "success-msg" att visa
        msgSuccess(buildSuccessMessage(name, date, time));

    }



    // Denna metod ska senare fråga databasen:
    // "Finns det något bord med kapacitet >= guests som INTE är bokat den tiden?"
    private boolean isTableAvailable(LocalDate date, String time, int guests) {
        // När DB är inkopplad: return bookingService.checkAvailability(date, time, guests);

        //testawr bara lite logik här, ta bort senare
        if (guests == 6) {
            return false;
        }

        return true; 
    }

    //meddelanden
    private void addError(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Felmeddelande: ", msg));
    }

    private void msgSuccess(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success! ", msg));
    }


    // metod för att bygga ihop meddelanden
    private String buildSuccessMessage(String name, LocalDate date, String time) {
        String who = (name == null || name.isBlank()) ? "Din bokning" : ("Tack " + name + "!");
        return who + " Bokningsförfrågan mottagen för " + date + " kl " + time + ".";
    }




    // Getters & Setters
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
