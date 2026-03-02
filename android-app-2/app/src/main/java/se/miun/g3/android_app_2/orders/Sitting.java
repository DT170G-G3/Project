package se.miun.g3.android_app_2.orders;
import se.miun.g3.android_app_2.tables.Table;
import java.time.LocalDate;
import java.time.LocalTime;

public class Sitting {
    public int id;
    public String startTime;
    public String date;
    public int durationMinutes;
    public Table restaurantTable;
}
