package com.dt170g.g3.backend.beans;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("menuViewBean")
@ViewScoped
public class MenuViewBean implements Serializable {

    // Lista med bilder.
    // Index 0, 3, 6, osv. = Starters
    // Index 1, 4, 7, osv. = Mains
    // Index 2, 5, 8, osv. = Desserts
    private static final List<String> DIVIDERS = List.of(
            "divider/starters1.jpg", "divider/mains1.jpg", "divider/desserts1.jpg",
            "divider/starters2.jpg", "divider/mains2.jpg", "divider/desserts2.jpg",
            "divider/starters3.jpg", "divider/mains3.jpg", "divider/desserts3.jpg"
    );

    // Hämtar bilderna för rätt kategori
    public List<String> getCarouselImagesForCategory(int categoryIndex) {
        List<String> categoryImages = new ArrayList<>();

        // Vilket tema, mod 0,1,2
        int targetMod = categoryIndex % 3;

        // Loopa igenom hela listan
        for (int i = 0; i < DIVIDERS.size(); i++) {
            // Om bildens index matchar kategorins mod så lägger vi till den
            if (i % 3 == targetMod) {
                categoryImages.add(DIVIDERS.get(i));
            }
        }

        return categoryImages;
    }
}