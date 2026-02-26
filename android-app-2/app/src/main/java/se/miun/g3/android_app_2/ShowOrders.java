package se.miun.g3.android_app_2;

import java.util.List;

public class ShowOrders {
    private int tableNumber;
    private List<String> starters;
    private List<String> mainCourses;
    private List<String> desserts;
    private String createdAt;
    private boolean startersDone;
    private boolean mainCoursesDone;
    private boolean dessertsDone;

    public ShowOrders(int tableNumber, List<String> starters, List<String> mainCourses, List<String> desserts) {
        this.tableNumber = tableNumber;
        this.starters = starters;
        this.mainCourses = mainCourses;
        this.desserts = desserts;
        this.createdAt = createdAt;

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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setStartersDone(boolean doneOrNot) {startersDone = doneOrNot; }
    public void setMainCoursesDone(boolean doneOrNot) {mainCoursesDone = doneOrNot; }
    public void setDessertsDone(boolean doneOrNot) {dessertsDone = doneOrNot; }
    public boolean isStartersDone() { return startersDone;}
    public boolean isMainCoursesDone() { return mainCoursesDone;}
    public boolean isDessertsDone() { return dessertsDone;}

}
