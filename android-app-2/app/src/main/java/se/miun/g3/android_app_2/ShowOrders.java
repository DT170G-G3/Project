package se.miun.g3.android_app_2;

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

    private long sortTime;

    private long starterDoneTime;
    private long mainCourseDoneTime;
    private long dessertDoneTime;

    public ShowOrders(int tableNumber, List<String> starters, List<String> mainCourses, List<String> desserts, String notes, String createdAt) {
        this.tableNumber = tableNumber;
        this.starters = starters;
        this.mainCourses = mainCourses;
        this.desserts = desserts;
        this.createdAt = createdAt;
        this.notes = notes;
        this.sortTime = System.currentTimeMillis();

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

    public long getStarterDoneTime() {return starterDoneTime;}
    public long getMainCourseDoneTime() {return mainCourseDoneTime;}
    public long getDessertDoneTime() {return dessertDoneTime;}
    public String getCreatedAt() { return createdAt; }
    public String getNotes() { return notes; }

    public long getSortTime() {return sortTime; }
    public void setStartersDone(boolean doneOrNot) {startersDone = doneOrNot; }
    public void setMainCoursesDone(boolean doneOrNot) {mainCoursesDone = doneOrNot; }
    public void setDessertsDone(boolean doneOrNot) {dessertsDone = doneOrNot; }

    public void setStarterDoneTime(long time) { this.starterDoneTime = time;}
    public void setMainCourseDoneTime(long time) { this.mainCourseDoneTime = time;}
    public void setDessertDoneTime(long time) { this.dessertDoneTime = time;}

    public void setSortTime(long sortTime) { this.sortTime = sortTime; }
    public boolean isStartersDone() { return startersDone;}
    public boolean isMainCoursesDone() { return mainCoursesDone;}
    public boolean isDessertsDone() { return dessertsDone;}

}
