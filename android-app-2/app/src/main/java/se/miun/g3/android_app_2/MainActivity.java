package se.miun.g3.android_app_2;

import static java.util.Collections.replaceAll;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import se.miun.g3.android_app_2.dishes.Dish;
import se.miun.g3.android_app_2.dishes.DishesRepository;
import se.miun.g3.android_app_2.drinks.Drink;
import se.miun.g3.android_app_2.drinks.DrinksRepository;
import se.miun.g3.android_app_2.orders.Order;
import se.miun.g3.android_app_2.orders.OrdersRepository;
import se.miun.g3.android_app_2.orders.Sitting;
import se.miun.g3.android_app_2.tables.Table;
import se.miun.g3.android_app_2.tables.TablesRepository;
import se.miun.g3.android_app_2.waiters.WaitersRepository;

public class MainActivity extends AppCompatActivity {
    DishesRepository dishesRepo = new DishesRepository();
    DrinksRepository drinksRepo = new DrinksRepository();
    TablesRepository tablesRepo = new TablesRepository();
    OrdersRepository ordersRepo = new OrdersRepository();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView orderRecyclerView = findViewById(R.id.orderRecyclerView);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //------POST--------
        //exampleCreateOrder();

        //-----/POST--------

        //-------GET--------
        asyncLoadTables();
        asyncLoadOrders();
        asyncLoadDishes();
        asyncLoadDrinks();
        //------/GET--------

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private ShowOrders orderToShowOrders(Order backendOrder) {
        List<String> starters = new ArrayList<>();
        List<String> mains = new ArrayList<>();
        List<String> desserts = new ArrayList<>();

        int tableNum;
        String notes;
        String time;
        for (Dish d : backendOrder.dishes) {
            if (d.category == null) {
                continue;
            }

            switch (d.category.id) {
                case 1:
                    starters.add(d.name);
                    break;
                case 2:
                    mains.add(d.name);
                    break;
                case 3:
                    desserts.add(d.name);
                    break;
                default:
                    Log.w("ORDER", "Okänd kategori: " + d.category.id);
            }
        }
        tableNum = backendOrder.table.tableNum;
        notes = backendOrder.note;
        time = backendOrder.createdAt;
        return new ShowOrders(tableNum, starters, mains, desserts, notes, time);
    }


    /**
     * Hämtar alla rätter (dishes) från backend och skickar resultatet till UI.
     * <p>
     * Vad den gör:
     * - Startar ett nätverksanrop via DishesRepository.
     * - När datan är klar: onSuccess() körs och skickar listan vidare till populateDishesUI(dishes).
     * - Om något går fel: onError() körs och felet loggas.
     * <p>
     * Viktigt:
     * - Den här metoden ger INTE tillbaka en lista direkt.
     * - Anropet tar tid (pga nätverk), så listan kommer först i onSuccess(...).
     */

    public void exampleCreateOrder() {
        // EXAMPLE how to POST an Order
        // Only ID is required for drinks, sitting and dishes

        //Create new order, initialize new Lists
        Order order = new Order();
        order.dishes = new ArrayList<>();
        order.drinks = new ArrayList<>();

        Dish dish = new Dish();
        dish.id = 3;

        Drink drink = new Drink();
        drink.id = 1;

        Table table = new Table();
        table.id = 1;

        order.dishes.add(dish);
        order.drinks.add(drink);
        order.table = table;

        asyncCreateOrder(order);
    }
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
    private void asyncLoadTables() {
        tablesRepo.getTables(new TablesRepository.GetCallback() {
            @Override
            public void onSuccess(List<Table> tables) {
                populateTablesUI(tables);
            }
            @Override
            public void onError(String message) {
                Log.e("TABLES", "Fel: " + message);
            }
        });
    }
    private void asyncLoadOrders() {
        ordersRepo.getOrders(new OrdersRepository.GetCallback() {
            @Override
            public void onSuccess(List<Order> backendOrders) {
                Log.d("ORDERS", "API gav " + backendOrders.size() + " orders");

                backendOrders.sort(Comparator.comparing(o -> o.createdAt));
                List<ShowOrders> uiOrders = new ArrayList<>();
                for (Order o : backendOrders) {
                    uiOrders.add(orderToShowOrders(o));
                }
                RecyclerView orderRecyclerView = findViewById(R.id.orderRecyclerView);
                orderRecyclerView.setAdapter(new ShowOrdersAdapter(uiOrders));
            }

            @Override
            public void onError(String message) {
                Log.e("ORDERS", "Fel: " + message);
            }
        });
    }
    private void asyncLoadDrinks() {
        drinksRepo.getDrinks(new DrinksRepository.GetCallback() {
            @Override
            public void onSuccess(List<Drink> drinks) {
                populateDrinksUI(drinks);
            }
            @Override
            public void onError(String message) {
                Log.e("ORDERS", "Fel: " + message);
            }
        });

    }
    private void asyncCreateOrder(Order order) {
        //asynchronous post the order to the database
        ordersRepo.postOrder(order, new OrdersRepository.PostCallback() {
            @Override
            public void onSuccess(Order postOrder) {
                Log.d("ORDER", "SUCCESSFULLY POSTED ORDER");
            }
            @Override
            public void onError(String message) {
                Log.e("ORDER", "FAILED TO POST: " + message);
            }
        });
    }

    /**
     * Tar emot listan med rätter och uppdaterar UI så köket kan se dem.
     */
    private void populateTablesUI(List<Table> tables) {
        // Your code here
        Log.d("TABLE", "Size: " +tables.size());
    }
    private void populateOrdersUI(List<Order> orders) {
    }
    public void populateDishesUI(List<Dish> dishes) {
        // Your UI code
        Log.d("DISH", "Size: " +dishes.size());
    }
    public void populateDrinksUI(List<Drink> drinks) {
        // YOur UI code
        Log.d("DRINK", "Size: " + drinks.size());
    }


}