package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(
        name = "booking",
        indexes = {
                @Index(name = "ix_booking_date_time", columnList = "date,start_time")
        }
)
@NamedQueries({
        @NamedQuery(
                name = "Booking.findByDate",
                query = "SELECT b FROM Booking b WHERE b.date = :date"
        )
})

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @NotNull
    @Column(name = "duration_minutes", nullable = false)
    private int durationMinutes;

    @NotNull
    @Column(name = "no_of_people", nullable = false)
    private int noOfPeople;

    @Column(name = "note")
    private String note;

    @NotNull
    @Column(nullable = false)
    private String name;

    private String email;

    @Column(name = "phone_no")
    private String phoneNo;

    public Booking() {}


    // --- Getters & Setters ---
    public Integer getId() { return id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public int getNoOfPeople() { return noOfPeople; }
    public void setNoOfPeople(int noOfPeople) { this.noOfPeople = noOfPeople; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }
}