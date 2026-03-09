package com.dt170g.g3.backend.utils;

import java.util.Arrays;
import java.util.List;

public class ProfanityFilter {

    // Lägg till de ord du vill filtrera här (håll dem i små bokstäver)
    private static final List<String> BAD_WORDS = Arrays.asList(
            // Svenska
            "fan", "jävla", "jävlar", "helvete", "skit", "fitta", "kuk", "kuken",
            "hora", "satan", "djävla", "knulla", "idiot", "bög", "cp",
            "kukhuve", "kukhuvud", "fittig", "rövhål", "skitstövel",

            // Engelska
            "fuck", "fucking", "shit", "bitch", "asshole", "dick",
            "cunt", "bastard", "slut", "whore", "motherfucker", "cock",
            "pussy", "wanker", "twat", "bullshit", "crap", "douchebag"
    );

    public static String filterText(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        String filtered = input;

        for (String word : BAD_WORDS) {
            // (?i) gör att den ignorerar stora/små bokstäver (fAn, FAN, fan fångas alla)
            // \b betyder "word boundary", så den klipper inte inuti andra oskyldiga ord
            String regex = "(?i)\\b" + word + "\\b";

            // Byt ut det fula ordet mot stjärnor
            filtered = filtered.replaceAll(regex, "***");
        }

        return filtered;
    }
}