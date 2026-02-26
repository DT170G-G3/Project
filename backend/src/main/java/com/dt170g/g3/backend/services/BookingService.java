

package com.dt170g.g3.backend.services;

/*
skapa bokning, createBooking(...)
    1. Hämta bord från db med rätt storlek utifrån sällskapet
    2. Loopa igenom alla bord som passar och kolla om det är ledigt.
        -Finns det lediga bord/sittning vald tid/datum?
        - return true/false
    3. Skapa bokningen vid true
        - Skapa en sittning utifrån info från formuläret och spara ned i db.
 */