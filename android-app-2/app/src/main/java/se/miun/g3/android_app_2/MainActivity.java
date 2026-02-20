package se.miun.g3.android_app_2;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import se.miun.g3.android_app_2.dishes.Dish;
import se.miun.g3.android_app_2.dishes.DishesRepository;
import se.miun.g3.android_app_2.orders.OrdersRepository;

public class MainActivity extends AppCompatActivity {
    DishesRepository dishesRepo = new DishesRepository();
    OrdersRepository ordersRepo = new OrdersRepository();   // not in use, for later


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        // Hämtar rätter från backend när appen startar.
        // När svaret kommer tillbaka (efter en stund) kallas populateDishesUI(...) automatiskt.
        asyncLoadDishes();




        RecyclerView orderRecyclerView = findViewById(R.id.orderRecyclerView);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //För test, byt till databaskoppling sen
        List<Orders> starterOrders = Arrays.asList(
                new Orders(1, Arrays.asList("Carpaccio", "Caprese"), new ArrayList<>(), new ArrayList<>()),
                new Orders(2, Arrays.asList("Bruschetta", "oliver", "chark"), new ArrayList<>(), new ArrayList<>()),
                new Orders(3, Arrays.asList("Bruschetta", "oliver", "chark"), new ArrayList<>(), new ArrayList<>()),
                new Orders(4, Arrays.asList("Bruschetta", "oliver", "chark"), new ArrayList<>(), new ArrayList<>()),
                new Orders(5, Arrays.asList("Bruschetta", "oliver", "chark"), new ArrayList<>(), new ArrayList<>()),
                new Orders(6, Arrays.asList("Bruschetta", "oliver", "chark"), new ArrayList<>(), new ArrayList<>()),
                new Orders(7, Arrays.asList("Bruschetta", "oliver", "chark"), new ArrayList<>(), new ArrayList<>()),
                new Orders(8, Arrays.asList("Bruschetta", "oliver", "chark"), new ArrayList<>(), new ArrayList<>()),
                new Orders(9, Arrays.asList("Bruschetta", "oliver", "chark"), new ArrayList<>(), new ArrayList<>())

        );

        List<Orders> mainCourserOrders = Arrays.asList(
                new Orders(1, new ArrayList<>(), Arrays.asList("Kött", "Fisk"), new ArrayList<>()),
                new Orders(2, new ArrayList<>(), Arrays.asList("Pasta", "kött"),  new ArrayList<>()),
                new Orders(3, new ArrayList<>(), Arrays.asList("Pasta", "kött"),  new ArrayList<>()),
                new Orders(4, new ArrayList<>(), Arrays.asList("Pasta", "kött"),  new ArrayList<>()),
                new Orders(5, new ArrayList<>(), Arrays.asList("Pasta", "kött"),  new ArrayList<>()),
                new Orders(6, new ArrayList<>(), Arrays.asList("Pasta", "kött"),  new ArrayList<>()),
                new Orders(7, new ArrayList<>(), Arrays.asList("Pasta", "kött"),  new ArrayList<>()),
                new Orders(8, new ArrayList<>(), Arrays.asList("Pasta", "kött"),  new ArrayList<>()),
                new Orders(9, new ArrayList<>(), Arrays.asList("Pasta", "kött"),  new ArrayList<>())

        );

        List<Orders> dessertOrders = Arrays.asList(
                new Orders(1, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Tiramisu")),
                new Orders(2, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Chokladpralin")),
                new Orders(3, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Chokladpralin")),
                new Orders(4, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Chokladpralin")),
                new Orders(5, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Chokladpralin")),
                new Orders(6, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Chokladpralin")),
                new Orders(7, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Chokladpralin")),
                new Orders(8, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Chokladpralin")),
                new Orders(9, new ArrayList<>(), new ArrayList<>(), Arrays.asList("Pannacotta", "Chokladpralin"))
        );


        List<Orders> finalList = setOrderList(starterOrders, mainCourserOrders, dessertOrders);
        orderRecyclerView.setAdapter(new OrdersAdapter(finalList));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private List<Orders> setOrderList(List<Orders> starters, List<Orders> mainCourse, List<Orders>desserts) {
        Map<Integer, List<String>> starterMap = new HashMap<>();
        Map<Integer, List<String>> mainCourseMap = new HashMap<>();
        Map<Integer, List<String>> dessertMap = new HashMap<>();

        for(Orders o : starters) {
            starterMap.putIfAbsent(o.getTableNumber(), new ArrayList<>());
            starterMap.get(o.getTableNumber()).addAll(o.getStarters());
        }

        for(Orders o : mainCourse) {
            mainCourseMap.putIfAbsent(o.getTableNumber(), new ArrayList<>());
            mainCourseMap.get(o.getTableNumber()).addAll(o.getMainCourses());
        }

        for(Orders o : desserts) {
            dessertMap.putIfAbsent(o.getTableNumber(), new ArrayList<>());
            dessertMap.get(o.getTableNumber()).addAll(o.getDesserts());
        }

        Set<Integer> allTables = new HashSet<>();
        allTables.addAll(starterMap.keySet());
        allTables.addAll(mainCourseMap.keySet());
        allTables.addAll(dessertMap.keySet());

        List<Orders> completeOrder = new ArrayList<>();
        for(Integer table : allTables) {
            completeOrder.add(new Orders(table, starterMap.getOrDefault(table, new ArrayList<>()),
                    mainCourseMap.getOrDefault(table, new ArrayList<>()),
                    dessertMap.getOrDefault(table, new ArrayList<>())
            ));
        }
        return completeOrder;
    }




    /**
     * Hämtar alla rätter (dishes) från backend och skickar resultatet till UI.
     *
     * Vad den gör:
     * - Startar ett nätverksanrop via DishesRepository.
     * - När datan är klar: onSuccess() körs och skickar listan vidare till populateDishesUI(dishes).
     * - Om något går fel: onError() körs och felet loggas.
     *
     * Viktigt:
     * - Den här metoden ger INTE tillbaka en lista direkt.
     * - Anropet tar tid (pga nätverk), så listan kommer först i onSuccess(...).
     */
    private void asyncLoadDishes() {
        dishesRepo.getDishes(new DishesRepository.GetCallback() {
            @Override
            public void onSuccess(List<Dish> dishes) {
                //DishesCache.setCache(dishes);   //om vi ska använda cache
                populateDishesUI(dishes);
            }
            @Override
            public void onError(String message) {
                Log.e("DISHES", "Fel: " + message);
            }
        });
    }


    /**
     * Tar emot listan med rätter och uppdaterar UI så köket kan se dem.
     */
    public void populateDishesUI(List<Dish> dishes) {
        // Your UI code
        Log.d("DISH", "Size: " +dishes.size()); // Visar i loggen att API:et är åtkomligt
    }
}