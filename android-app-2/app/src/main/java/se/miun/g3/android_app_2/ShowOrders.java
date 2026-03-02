package se.miun.g3.android_app_2;

import java.time.LocalTime;
import java.util.List;

public class ShowOrders {
    private int tableNumber;
    private List<String> starters;
    private List<String> mainCourses;
    private List<String> desserts;
    private String createdAt;

    private String notes;
    private boolean startersDone;
    private boolean mainCoursesDone;
    private boolean dessertsDone;

    private LocalTime starterDoneTime;
    private LocalTime mainCourseDoneTime;
    private LocalTime dessertDoneTime;

    public ShowOrders(int tableNumber, List<String> starters, List<String> mainCourses, List<String> desserts, String notes) {
        this.tableNumber = tableNumber;
        this.starters = starters;
        this.mainCourses = mainCourses;
        this.desserts = desserts;
        this.createdAt = createdAt;
        this.notes = notes;

    }
    public int getTableNumber() {
        return tableNumber;
    }

    public List<String> getStarters() {
        return starters;
    }
    public List<String> getMainCourses() {
        return mainCourses;
    }
    public List<String> getDesserts() {
        return desserts;
    }

    public LocalTime getStarterDoneTime() {return starterDoneTime;}
    public LocalTime getMainCourseDoneTime() {return mainCourseDoneTime;}
    public LocalTime getDessertDoneTime() {return dessertDoneTime;}
    public String getCreatedAt() { return createdAt; }
    public String getNotes() { return notes; }

    public void setStartersDone(boolean doneOrNot) {startersDone = doneOrNot; }
    public void setMainCoursesDone(boolean doneOrNot) {mainCoursesDone = doneOrNot; }
    public void setDessertsDone(boolean doneOrNot) {dessertsDone = doneOrNot; }

    public void setStarterDoneTime(LocalTime time) { this.starterDoneTime = time;}
    public void setMainCourseDoneTime(LocalTime time) { this.mainCourseDoneTime = time;}
    public void setDessertDoneTime(LocalTime time) { this.dessertDoneTime = time;}

    public boolean isStartersDone() { return startersDone;}
    public boolean isMainCoursesDone() { return mainCoursesDone;}
    public boolean isDessertsDone() { return dessertsDone;}

}
