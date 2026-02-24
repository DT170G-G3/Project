package com.DT170G.G3.android_app_1.dishes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// This class allows the app to save a local cache of dishes
// Used by Servitor and Cook apps for quick lookup
// without unnecessary retrofit calls
public class DishesCache {
    private static List<Dish> cacheList = new ArrayList<>();

    // Sets the cache through Retrofit call
    public static void setCache(List<Dish> dishes) {
        cacheList.clear();
        cacheList.addAll(dishes);
    }

    //Returns a list that is read only (edits are done elsewhere)
    // Dish objects are still modifiable (deal with this later)
    public static List<Dish> getCache() {
        return Collections.unmodifiableList(cacheList);
    }

    // Searches the list for a specific Dish, returns the Dish object with a matching ID
    public static Dish getDishById(int id) {
        for (Dish d : cacheList) {
            if (id == d.categoryId) {
                return d;
            }
        }
        return null;
    }
}
