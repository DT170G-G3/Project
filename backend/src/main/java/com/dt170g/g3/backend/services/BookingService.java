package com.dt170g.g3.backend.services;

import com.dt170g.g3.backend.entities.Booking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

@ApplicationScoped
public class BookingService {

    @PersistenceContext
    private EntityManager em;

    // hur många bord? Hårdkodat just nu
    private static final int TOTAL_TABLES = 8;

    @Transactional
    public boolean createIfAvailable(Booking newBooking) {

        // Hämta alla bokningar för aktuell dag
        List<Booking> bookingsToday = em.createNamedQuery("Booking.findByDate", Booking.class)
                .setParameter("date", newBooking.getDate())
                .getResultList();

        // Räkna antalet krockar denna dag
        long overlappingCount = bookingsToday.stream()
                .filter(existing -> overlaps(existing, newBooking))
                .count();

        // Är det färre krockar än bord? OK!
        if (overlappingCount < TOTAL_TABLES) {
            em.persist(newBooking);
            em.flush(); // Tvingar fram SQL-anropet. Om constraint krockar kastas PersistenceException här.
            return true;
        }

        return false; // Fullbokat
    }

    // Hjälpmetod för att kolla tidsöverlappning
    private boolean overlaps(Booking b1, Booking b2) {
        LocalTime start1 = b1.getStartTime();
        LocalTime end1 = start1.plusMinutes(b1.getDurationMinutes());

        LocalTime start2 = b2.getStartTime();
        LocalTime end2 = start2.plusMinutes(b2.getDurationMinutes());

        // (Start1 < Slut2) OCH (Slut1 > Start2)
        return start1.isBefore(end2) && end1.isAfter(start2);
    }
}