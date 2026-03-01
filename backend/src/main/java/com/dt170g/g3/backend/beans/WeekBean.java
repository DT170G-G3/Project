package com.dt170g.g3.backend.beans;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.time.format.TextStyle;
import java.util.Locale;

/*
 * The week-bean is used to calculate the current week and
 * the days of a week. These functions can then be used by the admin page.
 *
 */
@Named("week")
@ViewScoped
public class WeekBean implements Serializable {
    private int weekNumber;

    @PostConstruct
    public void init() {
        setWeekNumber(LocalDate.now().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
    }

    public void setWeekNumber(int num){
        this.weekNumber = num;
    }

    public int getWeekNumber(){
        return weekNumber;
    }

    /*
     * Used to get current week and upcoming two weeks week number.
     * Applied to the buttons in the admin view.
     */
    public List<Integer> getWeekNumbers(){
        int weekNumber = LocalDate.now().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        List<Integer> weekNumbers = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            weekNumbers.add(weekNumber + i);
        }
        return weekNumbers;
    }
    /* To recives the dates of mon-fri for any given weeknumber */
    private List<LocalDate> getDaysOfWeek(int week, int dayAmount){
        WeekFields weekFields = WeekFields.ISO;
        LocalDate startOfWeek = LocalDate.now()
                .withYear(LocalDate.now().getYear())
                .with(weekFields.weekOfYear(),week)
                .with(DayOfWeek.MONDAY);
        List<LocalDate> weekdays = new ArrayList<>();
        for(int i = 0; i < dayAmount; i++){
            weekdays.add(startOfWeek.plusDays(i));
        }
        return weekdays;
    }
    /* The method used by the facelet. */
    public List<LocalDate> getDaysOfWeekLunch(){
        return getDaysOfWeek(weekNumber,5);
    }

    public List<LocalDate> getDaysOfWeekSchedule(){
        return getDaysOfWeek(weekNumber,6);
    }

    // returnerar vilken dag i veckan
    public String getCurrentDay(){
        Locale sv = Locale.forLanguageTag("sv-SE");
        DayOfWeek dow = LocalDate.now().getDayOfWeek();

        if (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY) {
            return "Helg: ingen dagens lunch - Välkommen att beställa á la carte";
        }

        String day = dow.getDisplayName(TextStyle.FULL, sv);
        return day.substring(0, 1).toUpperCase(sv) + day.substring(1);
    }

}
